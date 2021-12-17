const userInfoTable = document.getElementById('user-info-table')
const userInfoBody = userInfoTable.querySelector('tbody');
const userInfoHeader = document.getElementById('userHeaderInfo');

const urlGetCurrentUser = ('http://localhost:8080/get-current-user');

const curUser = (user) => {
    const outputHeader =
        `
        <span class="nav_text_bold">${user.email}</span>
        with roles:
        <span class="nav_text_bold">${user.rolesToString}</span>
`;
    userInfoHeader.innerHTML = outputHeader;

    const outputTable =
        `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.rolesToString}</td>
        </tr>`;
    userInfoBody.innerHTML = outputTable;
}
//Get current user
fetch(urlGetCurrentUser)
    .then(response => response.json())
    .then(data => curUser(data));