

/* post details 편집 폼 토글 함수 */
function toggleEditForm(link) {
    var cardBody = $(link).closest('.card-body');
    cardBody.find('form').toggle();
    cardBody.find('p[id^="comment-"]').toggle();

    // 링크 클릭 시 스크롤 이동 방지
    event.preventDefault();
}

/* 편집 취소 함수 */
function cancelEdit(button, event) {
    var cardBody = $(button).closest('.card-body');
    cardBody.find('form').toggle();
    cardBody.find('p[id^="comment-"]').toggle();

    // 취소 버튼 클릭 시 스크롤 이동 방지
    event.preventDefault();
}

function toggleLikePost(button) {
    const postId = button.getAttribute("data-id");
    fetch(`/likes/posts/${postId}`, {
        method: "POST",
    })
        .then(response => response.text())
        .then(result => {
            button.textContent = result === "liked" ? "Unlike" : "Like";
        })
        .catch(error => console.error("Error:", error));
}

function fetchNotifications() {
    fetch('/notifications/unread')
        .then(response => response.json())
        .then(notifications => {
            const list = document.getElementById('notification-list');
            list.innerHTML = '';
            notifications.forEach(notification => {
                const li = document.createElement('li');
                li.textContent = notification.message;
                list.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching notifications:', error));
}

fetchNotifications();