loadMenuJs();
function loadMenuJs(){
 document
   .getElementById("slim-toggler")
   .addEventListener("click", () => {
     const instance = te.Sidenav.getInstance(
       document.getElementById("sidenav-main")
     );
     instance.toggleSlim();
   });

}