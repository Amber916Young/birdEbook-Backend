const customDatatable = document.getElementById("datatable");
const myModalEl = document.getElementById("formPopCard");
const modalInstance = new te.Modal(myModalEl);
var tagsAPI = tagsAPI

function SaveOrUpdate() {
    var tagName = $("#tagName").val();
    var tagIcon = $("#tagIcon").val();
    var tagColor = $("#tagColor").val();
    var tagId = $("#tagId").val();
    if (tagId === "-1" || tagId === -1) {
        var body = {
            "icon": tagIcon,
            "color": tagColor,
            "name": tagName
        };
        ajaxPost(tagsAPI, JSON.stringify(body), function (data) {
            clear()
        });
    } else {
        var body = {
            "id": tagId,
            "icon": tagIcon,
            "color": tagColor,
            "name": tagName
        };
        ajaxPut(tagsAPI, JSON.stringify(body), function (data) {
            clear()

        });
    }
}

function clear() {
    $("#tagName").val("")
    $("#tagIcon").val("")
    $("#tagColor").val("")
    $("#tagId").val(-1)
    $("#formPopCardLabel").html("新增")

    modalInstance.hide();
}


function deleteItem(id) {
    ajaxDelete(tagsAPI + "/" + id, "", function (data) {
    });
}

function onClose() {
    clear()
}

function requestTableData() {
    var url = adminTags
    ajaxGet(url, "", function (data) {
        formTables(data)
    });
}

function formTables(data) {
    const setActions = () => {
        document.querySelectorAll(".edit-btn").forEach((btn) => {
            btn.addEventListener("click", () => {
                var id = btn.attributes["data-te-dataId"].value;
                for (let item in data.content) {
                    var obj = data.content[item];
                    if (obj.id === parseInt(id)) {
                        $("#tagName").val(obj.name)
                        $("#tagIcon").val(obj.icon)
                        $("#tagColor").val(obj.color)
                        $("#tagId").val(obj.id)
                        $("#formPopCardLabel").html("修改")

                        break;
                    }
                }

            });
        });

        document.querySelectorAll(".delete-btn").forEach((btn) => {
            btn.addEventListener("click", () => {
                // deleteItem(btn.attributes["data-te-dataId"].value)
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
                {label: "id", field: "id", fixed: 'left'},
                {label: "名称", field: "name", width: 200},
                {label: "图标", field: "icon", width: 200},
                {label: "颜色", field: "color"},
                {label: "cateId", field: "cateId"},
                {label: "创建时间", field: "createTime", width: 250, format: formatTime},
                {label: "操作", field: "actions", sort: false, width: 150, fixed: 'right'},
            ],
            rows: data.content.map((row) => {
                return {
                    ...row,
                    actions: `
            <button
              type="button"
              data-te-ripple-init
              data-te-toggle="modal"
              data-te-target="#formPopCard"       
              data-te-ripple-color="dark"
              data-te-dataId=${row.id}
              class="edit-btn inline-block rounded-full border border-primary p-1.5 mr-1 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
                  <i class="fa-regular fa-pen-to-square"></i>
            </button>

            <button
              type="button"
              data-te-ripple-init
              data-te-ripple-color="light"
                        data-te-toggle="popconfirm"
                        data-te-message="是否删除"
                        data-te-cancel-text="取消"
                        data-te-ok-text="确认"
                        data-te-popconfirm-mode="modal"
                data-te-dataId=${row.id}
              class="delete-btn inline-block rounded-full border border-danger bg-danger text-white p-1.5 uppercase leading-normal shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]">
              <i class="fa-solid fa-trash-can"></i>
            </button>`,
                };
            }),
        },
        {loading: false}
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