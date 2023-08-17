function requestTableData(){
  var url = adminArticle
  ajaxGet(url,  "", function (data) {
      console.info(data)
      formTables(data)



  });
}

function formTables(data){
const customDatatable =   document.getElementById("datatable");
const setActions = () => {
  document.querySelectorAll(".call-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      console.log(`call ${btn.attributes["data-te-number"].value}`);
    });
  });

  document.querySelectorAll(".message-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      console.log(
        `send a message to ${btn.attributes["data-te-email"].value}`
      );
    });
  });
};

customDatatable.addEventListener("render.te.datatable", setActions);
const formatTime = (cell, value) => {
  const date = new Date(value);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
};
const formatStatus = (cell, value) => {
    `<button
                   type="button"
                   data-te-ripple-init
                   data-te-ripple-color="dark"
                   class="call-btn inline-block rounded-full border border-primary p-1.5 mr-1 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
                       <i class="fa-regular fa-pen-to-square"></i>
                 </button>`
};

new te.Datatable(
  customDatatable,
  {
    columns: [
      { label: "id", field: "id", fixed: 'left' },
      { label: "标题", field: "title" },
      { label: "简介", field: "description" ,width:200},
      { label: "浏览量", field: "viewCount" ,width:100},
      { label: "点赞量", field: "diggCount",width:100 },
      { label: "评论数", field: "commentCount" ,width:100},
      { label: "收藏量", field: "collectCount" ,width:100},
      { label: "封面", field: "coverImage" ,width:200},
      { label: "类型", field: "articleType" ,width:150},
      { label: "状态", field: "status" ,width:150,format: formatStatus },
      { label: "创建者", field: "createdBy",width:100 },
      { label: "创建者id", field: "userId",width:100 },
      { label: "创建时间", field: "createTime" ,width:200,format: formatTime },
      { label: "修改时间", field: "modifyTime" ,width:200,format: formatTime },
      { label: "操作", field: "actions", sort: false ,width:150, fixed: 'right'},
    ],
    rows: data.content.map((row) => {
      return {
        ...row,
        actions: `
            <button
              type="button"
              data-te-ripple-init
              data-te-ripple-color="dark"
              class="call-btn inline-block rounded-full border border-primary p-1.5 mr-1 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
                  <i class="fa-regular fa-pen-to-square"></i>
            </button>
            <button
              type="button"
              data-te-ripple-init
              data-te-ripple-color="light"
              class="message-btn inline-block rounded-full border border-success bg-success text-white p-1.5 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
              <i class="fa-regular fa-eye"></i>
            </button>
            <button
              type="button"
              data-te-ripple-init
              data-te-ripple-color="light"
              class="message-btn inline-block rounded-full border border-danger bg-danger text-white p-1.5 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
              <i class="fa-solid fa-trash-can"></i>
            </button>`,
      };
    }),
  },
  { loading: false }
);

}