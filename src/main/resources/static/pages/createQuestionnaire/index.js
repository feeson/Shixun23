onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建问卷')
  putProjectName()
}

const putProjectName = () => {
  // 获得所有的项目名，然后填入下拉列表中，
  let projectId = $util.getPageParam('createQuestionnaire')
  let params = {
    createdBy: $util.getItem('userInfo').username
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      projectList = res.data

      // 获取 select 元素
      const selectElement = document.getElementById('selectLeo0');

      // 遍历项目列表，为每个项目创建一个 option 元素并添加到 select 元素中
      projectList.forEach((project) => {
        const optionElement = document.createElement('option');
        optionElement.value = project.id; // 假设项目对象中有一个 id 属性表示项目的唯一标识
        optionElement.textContent = project.projectName; // 假设项目对象中有一个 name 属性表示项目的名称
        selectElement.appendChild(optionElement);

        if (project.id === projectId) {
          optionElement.selected = true; // 设置目标项目为默认选中项
        }
      });
    }
  })
}


const onCreateTemplate = () => {
  // let projectId = $util.getPageParam('createQuestionnaire')
  let projectId = $('#selectLeo0').val();
  const selectElement1 = document.getElementById('selectLeo1');
  const type = selectElement1.value;
  $util.setItem('projectId', projectId)
  $util.setItem('surveyType', type === '学生' ? 0 : 1)
  location.href = "/pages/createNewQuestionnaire/index.html"
}

const importHistoryQuestionnaire = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div>页面测试数据</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const surveyTypeTemplate = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">创建模板</div>
          <div>题库抽题，限时作答，成绩查询，自动阅卷</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="createTemplate()">创 建</button>
      </div>
    </div>
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div></div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="handleEdit()" style="margin-right: 10px;">编 辑</button>
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const createTemplate = () => {
  $('#createTemplateModal').modal('show')
}

const handleEdit = () => {
  open('/pages/designQuestionnaire/index.html')
}
