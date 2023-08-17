function requestTableData(){
  var url = ""
  ajaxPost(url, JSON.stringify(jsonData), function (data) {
            if (data.code == 0) {


            }else {
                layer.msg(data.msg, {time: 3000, icon: 5});
            }
        });
}