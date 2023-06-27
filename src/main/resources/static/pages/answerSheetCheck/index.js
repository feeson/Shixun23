let data = [];
let ids = []
let names = []

let projectId = $util.getPageParam('countProject')
onload = () => {
    let params = {
        projectId: projectId
    }
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data
            console.log(info, 'res')
            names = ['asdwenk', 'cewwef', 'piowen']
            for (let i = 0; i < info.length; i++) {
                let q = {}
                q.id = info[i].questionnaireName
                q.name = names[i]
                q.publishDate = info[i].releaseTime
                data.push(q)
                ids.push(info[i].id)
            }
            updateQuestion()
        }
    })
}
function searchQues(flag) {
    switch (flag){
        case 0:{
            $("#meta2").hide();
            $("#meta3").hide();
            break;
        }
        case 1:{
            $("#meta2").show()
            $("#meta3").show();
            break;
        }
    }

    let params = {
        projectId: projectId
    }
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data
            console.log(info, 'res')
            names = ['asdwenk', 'cewwef', 'piowen']
            for (let i = 0; i < info.length; i++) {
                let q = {}
                q.id = info[i].questionnaireName
                q.name = names[i]
                q.publishDate = info[i].releaseTime
                data.push(q)
                ids.push(info[i].id)
            }
            data.splice(1, 1);
            updateQuestion()
        }
    })

}

function countStats(i) {
    location.href = '/pages/answerSheet/index.html'
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

        var statsBtn = document.createElement('button');
        statsBtn.type = 'button';
        statsBtn.className = 'btn btn-link btn-red';
        statsBtn.textContent = '明细';
        statsBtn.addEventListener('click', countStats(i)); // 添加事件处理程序

        actionCell.append(statsBtn);
        row.appendChild(actionCell);

        tableBody.appendChild(row);
    }
}
