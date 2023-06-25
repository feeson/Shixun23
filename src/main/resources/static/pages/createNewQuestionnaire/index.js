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

const putProjectName = () => {
  // 获得所有的项目名，然后填入下拉列表中，
  let projectId = $util.getPageParam('createQuestionnaire')

}

const onCreate = () => {
  let params = {
    createdBy: $util.getItem('userInfo').username,
    lastUpdatedBy: $util.getItem('userInfo').username,
    Name: $('#projectName').val(),
    projectContent: $('#projectDescribe').val()
  }
  if (!params.projectName) return alert('项目名称不能为空！')
  if (!params.projectContent) return alert('项目描述不能为空！')
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

