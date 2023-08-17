function requestTableData(){
  var url = ""
  ajaxPost(url, JSON.stringify(jsonData), function (data) {
            if (data.code == 0) {


            }else {
                layer.msg(data.msg, {time: 3000, icon: 5});
            }
        });
}

function formTables(){
const customDatatable = $("#datatable");
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

new te.Datatable(
  customDatatable,
  {
    columns: [
      { label: "Name", field: "name" },
      { label: "Position", field: "position" },
      { label: "Office", field: "office" },
      { label: "Office", field: "office" },
      { label: "Office", field: "office" },
      { label: "Contact", field: "contact", sort: false },
    ],
    rows: [
      {
        name: "Tiger Nixon",
        position: "System Architect",
        office: "Edinburgh",
      },
      {
        name: "Sonya Frost",
        position: "Software Engineer",
        office: "Edinburgh",
      },
      {
        name: "Tatyana Fitzpatrick",
        position: "Regional Director",
        office: "London",
      },
    ].map((row) => {
      return {
        ...row,
        contact: `
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
  { hover: true }
);

}