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
// $(document).ready(onCreate = () => {
//   let params = {
//     createdBy: $util.getItem('userInfo').username,
//     lastUpdatedBy: $util.getItem('userInfo').username,
//
//     projectId: $util.getItem('projectId'),
//     questionnaireName: $('#surveyName').val(),
//     questionnaireDescription: $('#surveyDescription').val(),
//     surveyType: $util.getItem('surveyType'),
//     releaseTime: $('#startTime').val(),
//     deadline: $('#endTime').val()
//   }
//   if (!params.questionnaireName) return alert('问卷名称不能为空！')
//   if (!params.questionnaireDescription) return alert('问卷描述不能为空！')
//   $.ajax({
//     url: API_BASE_URL + '/addQuestionnaire',
//     type: "POST",
//     data: JSON.stringify(params),
//     dataType: "json",
//     contentType: "application/json",
//     success(res) {
//       if (res.code === '200') {
//         // $util.setItem('userInfo', res.data)
//         location.href = "/pages/designQuestionnaire/index.html"
//       } else {
//         alert(res.message)
//       }
//     }
//   })
//   // location.href = "/pages/designQuestionnaire/index.html"
// })

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
        // $util.setItem('userInfo', res.data)
        location.href = "/pages/designQuestionnaire/index.html"
      } else {
        alert(res.message)
      }
    }
  })
  // location.href = "/pages/designQuestionnaire/index.html"
}

