function requestTableData(){
  var url = adminArticles
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

const instance = new te.Datatable(
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
      { label: "状态", field: "status" ,width:150,format: function (row, column, value) {
                                                            return `
                                                              <div
                                                                data-te-chip-init
                                                                data-te-ripple-init
                                                                class="word-wrap break-word my-[5px] mr-4 flex h-[32px] cursor-pointer items-center justify-between rounded-[16px] border border-[#9fa6b2] bg-[#eceff1] bg-[transparent] px-[12px] py-0 text-[13px] font-normal normal-case leading-loose text-[#4f4f4f] shadow-none transition-[opacity] duration-300 ease-linear hover:border-[#9fa6b2] hover:!shadow-none dark:text-neutral-200"
                                                                data-te-ripple-color="dark">
                                                                ${value}
                                                                <span
                                                                  data-te-chip-close
                                                                  class="float-right w-4 cursor-pointer pl-[8px] text-[16px] text-[#afafaf] opacity-[.53] transition-all duration-200 ease-in-out hover:text-[#8b8b8b] dark:text-neutral-400 dark:hover:text-neutral-100">
                                                                  <svg
                                                                    xmlns="http://www.w3.org/2000/svg"
                                                                    fill="none"
                                                                    viewBox="0 0 24 24"
                                                                    stroke-width="1.5"
                                                                    stroke="currentColor"
                                                                    class="h-3 w-3">
                                                                    <path
                                                                      stroke-linecap="round"
                                                                      stroke-linejoin="round"
                                                                      d="M6 18L18 6M6 6l12 12" />
                                                                  </svg>
                                                                </span>
                                                              </div>
                                                            `;
                                                          } },
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







const searchInput = document.getElementById('search-input');

  const search = (value) => {
    let [phrase, columns] = value.split(" in:").map((str) => str.trim());

    if (columns) {
      columns = columns.split(",").map((str) => str.toLowerCase().trim());
    }

    instance.search(phrase, columns);
  };

  document
    .getElementById("search-button")
    .addEventListener("click", (e) => {
      search(searchInput.value);
    });

  searchInput.addEventListener("keydown", (e) => {
    if (e.keyCode === 13) {
      search(e.target.value);
    }
  });
}