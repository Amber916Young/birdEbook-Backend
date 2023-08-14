function show_runtime() {
    window.setTimeout("show_runtime()", 1000);
    X = new
    Date("06/06/2023 08:07:48");
    Y = new Date();
    T = (Y.getTime() - X.getTime());
    M = 24 * 60 * 60 * 1000;
    a = T / M;
    A = Math.floor(a);
    b = (a - A) * 24;
    B = Math.floor(b);
    c = (b - B) * 60;
    C = Math.floor((b - B) * 60);
    D = Math.floor((c - C) * 60);
    runtime_span.innerHTML = "论坛已运行 " + A + "天" + B + "小时" + C + "分" + D + "秒"
}

const baseUrl = "http://localhost:8080/api";

function sendAjaxRequest(url, method, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                callback(null, xhr.responseText);
            } else {
                callback('Error: ' + xhr.status);
            }
        }
    };

    xhr.open(method, baseUrl + url, true);
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Access-Control-Allow-Private-Network', 'true');
    xhr.setRequestHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');

    if (method === 'POST' || method === 'PUT') {
        xhr.setRequestHeader('Content-Type', 'application/json');
    }
    xhr.send();

    // xhr.send(JSON.stringify(data));
}

function convertImageToBase64(imageUrl, callback) {
    fetch(imageUrl)
        .then(response => response.blob())
        .then(blob => {
            const reader = new FileReader();
            reader.onload = function () {
                const base64Data = reader.result.split(',')[1];
                callback(base64Data);
            };
            reader.readAsDataURL(blob);
        })
        .catch(error => {
            console.error('Error converting image to Base64:', error);
        });
}

function transfer(button) {
    var postElement = button.closest('.Post');
    if (postElement) {
        var postBody = postElement.querySelector('.Post-body');
        if (postBody) {
            var postContent = postBody.innerHTML.trim();
            var authorNameElement = postElement.querySelector('.username');
            var authorName = authorNameElement ? authorNameElement.textContent : null;
            var avatarImg = postElement.querySelector('.PostUser-avatar');
            var avatarUrl = avatarImg ? avatarImg.getAttribute('src') : null;
            var postDateElement = postElement.querySelector('time');
            var titleAttribute = postDateElement ? postDateElement.getAttribute('title') : null;
            console.log("Post Content:", postContent);
            console.log("Author:", authorName);
            console.log("Avatar URL:", avatarUrl);
            console.log("postDate:", titleAttribute);
            var base64 = "";
            // Call the function to convert the image to Base64
            convertImageToBase64(avatarUrl, function (base64Data) {
                base64 = base64Data;
            });
            const postData = {
                username: waterMarkUsername,
                content: postContent,
                avatar: base64,
                author: authorName,
                postDate: titleAttribute,
            };

            sendAjaxRequest('/flarum/test', 'GET', postData, function (error, response) {
                if (error) {
                    console.error(error);
                } else {
                    console.log('Response:', response);
                }
            });
            // sendAjaxRequest('/flarum/generate', 'POST', postData, function (error, response) {
            //     if (error) {
            //         console.error(error);
            //     } else {
            //         console.log('Response:', response);
            //     }
            // });
        }
    }
}

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

function generalTransferDom() {
    var firstPostActionContent = `
  <li class="Dropdown-separator"></li>  <li class="item-transfer">
        <button onclick="transfer(this)"  class="hasIcon"  type="button">
            <i aria-hidden="true" class="icon fas fa-share Button-icon"></i>
            <span class="Button-label">转发</span>
        </button>
    </li>
`;
    var postActionsLists = document.querySelectorAll('.Post-controls ul');
    postActionsLists.forEach(function (ulElement) {
        var existingTransferButton = ulElement.querySelector('.item-transfer');
        if (!existingTransferButton) {
            ulElement.insertAdjacentHTML('beforeend', firstPostActionContent);
        }
    });

}

function handleDropdownClick(event) {
    generalTransferDom();
}

var waterMarkUsername = "visitor"
document.addEventListener('DOMContentLoaded', function () {
    waterMarkUsername = document.getElementsByClassName("username")[0].innerHTML;
    __canvasWM({
        content: waterMarkUsername
    })
    show_runtime();
});

// Add event delegation to a parent element that is already present when the page loads
document.body.addEventListener('click', function (event) {
    var target = event.target;
    if ((target.classList.contains('Dropdown-toggle')
        && target.classList.contains('Button')
        && target.classList.contains('Button--icon')
        && target.classList.contains('Button--flat')
    ) || (target.classList.contains('icon')
        && target.classList.contains('fas')
        && target.classList.contains('fa-ellipsis-h')
        && target.classList.contains('Button-icon')
    )) {
        handleDropdownClick(event);
    }
});







