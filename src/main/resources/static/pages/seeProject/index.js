onload = () => {
    $('#headerDivB').text('项目详情')

    let projectId = $util.getPageParam('seeProject')
    console.log(projectId, 'projectId')
    fetchProjectInfo(projectId)
    fetchQuesData(projectId)

}
let ids = []

const fetchProjectInfo = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data[0]
            console.log(info, 'res')
            $('#projectName').text(info.projectName)
            $('#createTime').text(info.creationDate)
            $('#projectDescription').text(info.projectContent)
        }
    })
}

let data = [];
// { id: 1, name: '问卷1', publishDate: '2023-06-25' },
// { id: 1, name: '问卷2', publishDate: '2023-06-25' },


const fetchQuesData = (id) => {
    let params = {
        projectId: id
    }
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data

            for (let i = 0; i < info.length; i++) {
                let q = {}
                q.id = i + 1
                q.name = info[i].questionnaireName
                q.publishDate = info[i].releaseTime
                data.push(q)
                ids.push(info[i].id)
            }
            console.log('data\n\n' + data)

            updateQuestion()

        }
    })
}

function updateQuestion() {
    // 遍历数据数组，逐个创建表格行并填充数据
    for (var i = 0; i < data.length; i++) {
        var row = document.createElement('tr');

        var idCell = document.createElement('td');
        idCell.textContent = data[i].id;
        row.appendChild(idCell);

        var nameCell = document.createElement('td');
        nameCell.textContent = data[i].name;
        row.appendChild(nameCell);

        var publishDateCell = document.createElement('td');
        publishDateCell.textContent = data[i].publishDate;
        row.appendChild(publishDateCell);


        var actionCell = document.createElement('td');

        var previewBtn = document.createElement('button');
        previewBtn.type = 'button';
        previewBtn.className = 'btn btn-link';
        previewBtn.textContent = '预览';
        previewBtn.addEventListener('click', preview(i)); // 添加事件处理程序

        var publishBtn = document.createElement('button');
        publishBtn.type = 'button';
        publishBtn.className = 'btn btn-link';
        publishBtn.textContent = '发布';
        // publishBtn.onclick = publish;
        publishBtn.addEventListener('click', publish(i)); // 添加事件处理程序

        var deleteBtn = document.createElement('button');
        deleteBtn.type = 'button';
        deleteBtn.className = 'btn btn-link btn-red';
        deleteBtn.textContent = '删除';
        publishBtn.addEventListener('click', deleteQues(i)); // 添加事件处理程序

        var statsBtn = document.createElement('button');
        statsBtn.type = 'button';
        statsBtn.className = 'btn btn-link btn-red';
        statsBtn.textContent = '统计';
        publishBtn.addEventListener('click', count(i)); // 添加事件处理程序

        actionCell.append(previewBtn, publishBtn, deleteBtn, statsBtn);
        row.appendChild(actionCell);

        tableBody.appendChild(row);
    }
}

// 获取表格的 tbody 元素
var tableBody = document.querySelector('table tbody');

function preview(i) {
    location.href = '/pages/answerSheet/index.html'
}

function publish(i) {
    let params = {
        id: ids[i]
    }
    let accessLink;
    $.ajax({
        url: API_BASE_URL + '/publishQuestionnaire',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            accessLink = res.data.accessLink
        }
    })

    window.alert("发布成功！访问链接为: " + accessLink)
}

function deleteQues(i) {
    data.splice(i, 1);

    updateQuestion()
}


function count(i) {
    location.href = 'pages/answerSheetCheck/answer.html'
}