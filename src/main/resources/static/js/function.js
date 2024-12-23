

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