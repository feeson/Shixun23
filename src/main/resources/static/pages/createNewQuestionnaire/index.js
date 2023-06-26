onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}

const onCreate = () => {
  let params = {
    createdBy: $util.getItem('userInfo').username,
    lastUpdatedBy: $util.getItem('userInfo').username,

    projectId: $util.getItem('projectId'),
    questionnaireName: $('#surveyName').val(),
    questionnaireDescription: $('#surveyDescription').val(),
    surveyType: $util.getItem('surveyType'),
    releaseTime: $('#startTime').val(),
    deadline: $('#endTime').val()
  }
  // console.log($('#startTime').val())
  // console.log($('#endTime').val())

  if (!params.questionnaireName) return alert('问卷名称不能为空！')
  if (!params.questionnaireDescription) return alert('问卷描述不能为空！')
  $.ajax({
    url: API_BASE_URL + '/addQuestionnaire',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if (res.code === '200') {
        let questionnaire = {}

        // 解析后端返回的QuestionnaireEntity对象
        const backendQuestionnaire = res.data;

        // 将后端对象的属性赋值给前端的questionnaire对象
        questionnaire.id = backendQuestionnaire.id;
        questionnaire.projectId = backendQuestionnaire.projectId;
        questionnaire.questionnaireName = backendQuestionnaire.questionnaireName;
        questionnaire.questionnaireDescription = backendQuestionnaire.questionnaireDescription;
        questionnaire.surveyType = backendQuestionnaire.surveyType;
        questionnaire.releaseTime = new Date(backendQuestionnaire.releaseTime);
        questionnaire.deadline = new Date(backendQuestionnaire.deadline);
        questionnaire.accessLink = backendQuestionnaire.accessLink;
        questionnaire.deleteFlag = backendQuestionnaire.deleteFlag;
        questionnaire.createdBy = backendQuestionnaire.createdBy;
        questionnaire.creationDate = new Date(backendQuestionnaire.creationDate);
        questionnaire.lastUpdatedBy = backendQuestionnaire.lastUpdatedBy;
        questionnaire.lastUpdateDate = new Date(backendQuestionnaire.lastUpdateDate);
        questionnaire.questions = backendQuestionnaire.questions;

        $util.setItem('currentQuestionnaire', questionnaire)
        location.href = "/pages/designQuestionnaire/index.html"
      } else {
        alert(res.message)
      }
    }
  })
  // location.href = "/pages/designQuestionnaire/index.html"
}

