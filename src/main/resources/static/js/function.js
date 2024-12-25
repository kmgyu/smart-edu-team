

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

document.addEventListener("DOMContentLoaded", function () {
    // 모든 댓글의 좋아요 수를 가져와 업데이트
    document.querySelectorAll('[id^="like-post-"]').forEach(function (span) {
        const postId = span.id.split('-')[2]; // Extract comment ID from the ID attribute
        fetch(`/likes/like-post/${postId}`)
            .then(response => response.text())
            .then(likeCount => {
                span.textContent = likeCount;
            })
            .catch(error => console.error("Error fetching like count:", error));
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // 모든 댓글의 좋아요 수를 가져와 업데이트
    document.querySelectorAll('[id^="like-comment-"]').forEach(function (span) {
        const commentId = span.id.split('-')[2]; // Extract comment ID from the ID attribute
        fetch(`/likes/like-comment/${commentId}`)
            .then(response => response.text())
            .then(likeCount => {
                span.textContent = likeCount;
            })
            .catch(error => console.error("Error fetching like count:", error));
    });
});
