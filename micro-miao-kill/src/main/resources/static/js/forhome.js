









// var info=""
// $(function () {
//     on_on(1)
// })

// function seach() {
//     info=$("#info").val();
//     on_on(1)
// }
// function on_on(i) {
//     $.get("getGoods",{"info":"","i":i},function (data) {

// console.log(data)
// $("#datalist").html("")
// $.each(data.goods,function (i,e) {
// $("#datalist").append(" <li>" +
//     "<img src='"+e.goodsimg+"' style='border-radius: 6px;width: 98%;height: 72%;float: left;margin: 1% 1%;position: relative;'>" +
//     "<div style='border-radius: 6px;text-align: center;background-color: rgba(26,255,0,0.09);width: 80%;height: 10%;float: left;margin: 0 10%;position: relative;'>品名：<a href='#' style='text-decoration: none;color: rgba(20,23,207,0.83);height: 100%;font-weight: 660'>"+e.goodsname+"</a></div>\n" +
//     "<div style='border-radius: 6px; text-align: center;background-color: rgba(208,209,212,0.18);width: 98%;height: 12%;float: left;margin: 1%;position: relative;'>" +
//     "<a href='#' style='text-decoration: none;color: rgba(203,24,24,0.82);line-height: 200%'>"+e.goodscontent+"</a>" +
//     " 单价：<a href='#' style='text-decoration: none;color: rgb(255,1,1);font-weight: 660'>"+e.goodsprice+"</a>" +
//     " 库存：<a href='#' style='text-decoration: none;font-weight: 400;color: "+(e.goodsstock<100?"red":"green")+"'>"+e.goodsstock+"</a></div>\n" +
//     "        </li>")
// })
//     },"json")
// }