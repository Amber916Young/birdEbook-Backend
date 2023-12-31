
function __canvasWM({
                        container = document.body,
                        width = '200px',
                        height = '150px',
                        textAlign = 'center',
                        textBaseline = 'middle',
                        font = "20px microsoft yahei",
                        fillStyle = 'rgba(184,184,184,0.06)',
                        content = '请勿外传',
                        rotate = '30',
                        zIndex = 1000
                    } = {}) {
    var args = arguments[0];
    var canvas = document.createElement('canvas');

    canvas.setAttribute('width', width);
    canvas.setAttribute('height', height);
    var ctx = canvas.getContext("2d");

    ctx.textAlign = textAlign;
    ctx.textBaseline = textBaseline;
    ctx.font = font;
    ctx.fillStyle = fillStyle;
    ctx.rotate(Math.PI / 180 * rotate);
    ctx.fillText(content, parseFloat(width) / 2, parseFloat(height) / 2);

    var base64Url = canvas.toDataURL();
    const watermarkDiv = document.createElement("div");
    watermarkDiv.setAttribute('style', `
          position:absolute;
          top:0;
          left:0;
          width:100%;
          height:100%;
          z-index:${zIndex};
          pointer-events:none;
          background-repeat:repeat;
          background-image:url('${base64Url}')`);
    container.style.position = 'relative';
    container.insertBefore(watermarkDiv, container.firstChild);
}
window.__canvasWM = __canvasWM;

var waterMarkUsername = "visitor"
document.addEventListener('DOMContentLoaded', function () {
    waterMarkUsername = document.getElementsByClassName("username")[0].innerHTML;
    __canvasWM({
        content: waterMarkUsername
    })
});