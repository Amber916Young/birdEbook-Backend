function requestTableData(){
  var url = adminTags
  ajaxGet(url,  "", function (data) {
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
      { label: "名称", field: "name" },
      { label: "图标", field: "icon" ,width:100},
      { label: "颜色", field: "color" },
      { label: "cateId", field: "cateId" },
      { label: "创建时间", field: "createTime" ,width:200,format: formatTime },
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
              class="message-btn inline-block rounded-full border border-danger bg-danger text-white p-1.5 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
              <i class="fa-solid fa-trash-can"></i>
            </button>`,
      };
    }),
  },
  { loading: false }
);







const advancedSearchInput = document.getElementById('search-input');

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
      search(advancedSearchInput.value);
    });

  advancedSearchInput.addEventListener("keydown", (e) => {
    if (e.keyCode === 13) {
      search(e.target.value);
    }
  });
}