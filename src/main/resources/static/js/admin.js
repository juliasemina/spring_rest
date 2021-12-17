const tBody = document.querySelector("tbody")

const newUser = document.querySelectorAll(".create-user")
const usersTable = document.querySelectorAll(".users-table")

const tableContainer = document.getElementById("table-container")

//--------форма создания нового юзера
const createUserForm = document.querySelector(".add-user-post-form")

//--------------url для fetch
const urlGetUsers = "http://localhost:8080/admin/getusers"
const urlGetAllRoles = "http://localhost:8080/admin/get-all-roles"
const urlCreateUser = "http://localhost:8080/admin/create-user"
const urlGetUserById = "http://localhost:8080/admin/user"
const urlUpdateUser = "http://localhost:8080/admin/update-user"
const urlDeleteUser = "http://localhost:8080/admin/delete"

//--------------------------
const userName = document.getElementById("name")
const userSurname = document.getElementById("surname")
const userAge = document.getElementById("age")
const userEmail = document.getElementById("email")
const userPass = document.getElementById("pass")
const userRoleSelect = document.getElementById("allRoles")
//--------------------------------

let modal = ""
let modalForm = ""
let submitBtn = ""
let newPass = ""

let output = ``
const renderTable = (users) => {
    users.forEach((user) => {
        output += `
           <tr>
  <td>${user.id}</td>
  <td>${user.name}</td>
  <td>${user.surname}</td>
  <td>${user.age}</td>
  <td>${user.email}</td>
  <td>${user.rolesToString}</td>
  <td data-id="${user.id}">
    <button
      id="edit-button"
      class="action-button edit-button"
      data-toggle="modal"
      data-target="#editModal"
    >
      Edit
    </button>

    <div
      id="editModal"
      data-id="${user.id}"
      class="modal fade"
      tabindex="-1"
      aria-labelledby="editModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">Edit user</h5>
            <button
              type="button"
              class="btn-close close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form data-id="editForm" class="edit-user-put-form">
              <div>
                <label for="id" class="form-label">Id</label>
                <input
                  type="text"
                  id="id"
                  class="form-control"
                  placeholder="Id"
                  required
                  disabled
                />
              </div>
              <div>
                <label for="name" class="form-label">First name</label>
                <input
                  type="text"
                  id="name"
                  class="form-control"
                  placeholder="First name"
                  required
                />
              </div>

              <div>
                <label for="surname" class="form-label">Last name</label>
                <input
                  type="text"
                  id="surname"
                  class="form-control"
                  placeholder="Last name"
                  required
                />
              </div>

              <div>
                <label for="age" class="form-label">Age</label>
                <input
                  type="text"
                  id="age"
                  class="form-control"
                  placeholder="Age"
                  required
                />
              </div>

              <div>
                <label for="email" class="form-label">Email</label>
                <input
                  type="text"
                  id="email"
                  class="form-control"
                  placeholder="Email"
                  required
                />
              </div>

              <div>
                <label for="passNew" class="form-label">Password</label>
                <input
                  type="password"
                  id="pass"
                  class="form-control d-none"
                  placeholder="Password"
                />
              </div>
              <div>
                <input
                  type="password"
                  id="newPass"
                  value="password"
                  class="newPass form-control"
                  placeholder="Password"
                />
              </div>

              <div class="mb-3">
                <label for="allRoles" class="form-label">Role</label>
                <select
                  class="form-select"
                  id="allRoles"
                  name="allRolesName"
                  multiple
                ></select>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-close btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              type="submit"
              form="editForm"
              class="btn btn-primary submit"
              data-dismiss="modal"
            >
              Edit
            </button>
          </div>
        </div>
      </div>
    </div>
  </td>

  <td data-id="${user.id}">
    <button
      id="delete-button"
      class="action-button delete-button"
      data-toggle="modal"
      data-target="#deleteModal">
      Delete
    </button>
    <div id="deleteModal" class="modal fade" data-id="${user.id}" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="deleteModalLabel">Delete user</h5>
            <button
              type="button"
              class="btn-close close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="delete-user-form">
              <div>
                <label for="id" class="form-label">Id</label>
                <input
                  type="text"
                  id="id"
                  class="form-control"
                  placeholder="Id"
                  required
                  disabled
                />
              </div>
              <div>
                <label for="name" class="form-label">First name</label>
                <input
                  type="text"
                  id="name"
                  class="form-control"
                  placeholder="First name"
                  required
                  disabled
                />
              </div>

              <div>
                <label for="surname" class="form-label">Last name</label>
                <input
                  type="text"
                  id="surname"
                  class="form-control"
                  placeholder="Last name"
                  required
                  disabled
                />
              </div>

              <div>
                <label for="age" class="form-label">Age</label>
                <input
                  type="text"
                  id="age"
                  class="form-control"
                  placeholder="Age"
                  required
                  disabled
                />
              </div>

              <div>
                <label for="email" class="form-label">Email</label>
                <input
                  type="text"
                  id="email"
                  class="form-control"
                  placeholder="Email"
                  required
                  disabled
                />
              </div>

              <div>
                <label for="newPass" class="form-label">Password</label>
                <input
                  type="password"
                  id="pass"
                  class="form-control d-none"
                  placeholder="Password"
                  disabled
                />
              </div>
              <div>
                <input
                  type="password"
                  id="newPass"
                  value="password"
                  class="newPass form-control"
                  placeholder="Password"
                  disabled
                />
              </div>

              <div class="mb-3">
                <label for="allRoles" class="form-label">Role</label>
                <select
                disabled
                  class="form-select"
                  id="allRoles"
                  name="allRolesName"
                  multiple
                ></select>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-close btn-secondary action-button"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              type="submit"
              class="btn btn-primary submit"
              data-dismiss="modal"
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    </div>
  </td>
</tr>

`})

    tBody.innerHTML = output
}

//Get all users
fetch(urlGetUsers)
    .then((response) => response.json())
    .then((data) => renderTable(data))

// Get all roles
fetch(urlGetAllRoles)
    .then((response) => response.json())
    .then((data) => setSelectOpt(data))

const setSelectOpt = (roles) => {
    userRoleSelect.size = roles.length
    const options = roles

    for (let i = 0; i < options.length; i++) {
        let roleName = options[i].name
        let el = document.createElement("option")
        el.textContent = roleName
        el.value = roleName
        userRoleSelect.appendChild(el)
    }
}

createUserForm.addEventListener("submit", (e) => {
    e.preventDefault()
    output="";
    // '...' переводит в массив значение HTML элемента
    let selectedValues = [...userRoleSelect.options]
        .filter((x) => x.selected)
        .map((x) => x.value)

    fetch(urlCreateUser, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: userName.value,
            surname: userSurname.value,
            age: userAge.value,
            email: userEmail.value,
            pass: userPass.value,
            roles: selectedValues,
        }),
    })
        .then((response) => response.json())
        .then((data) => renderTable(data))

    createUserForm.reset()

    newUser.forEach((u) => u.classList.remove("active"))
    usersTable.forEach((u) => u.classList.add("active"))
})

tableContainer.addEventListener("click", (e) => {
    e.preventDefault()

    let editButtonIsPressed = e.target.id == "edit-button"
    let deleteButtonIsPressed = e.target.id == "delete-button"

    let id = e.target.parentElement.dataset.id

    if (deleteButtonIsPressed) {
        getModal(id, "delete")
    }
    if (editButtonIsPressed) {
        getModal(id, "edit")
    }

    function getModal(id, mode) {
        if (mode === "edit") {
            modal = document.getElementById("editModal")
            modalForm = modal.querySelector(".edit-user-put-form")
            submitBtn = modal.querySelector(".submit")
            newPass = modal.querySelector(".newPass")
            modal.remove();
        }
        if (mode === "delete") {
            modal = document.getElementById("deleteModal")
            modalForm = modal.querySelector(".delete-user-form")
            submitBtn = modal.querySelector(".submit")
            modal.remove();
        }
        const btnClose = modal.querySelectorAll(".btn-close")
        btnClose.forEach((e) => {
            e.addEventListener("click", (e) => {
                e.preventDefault()
                modal.remove()
            })
        })
        output = ""
        let pass="";

        fetch(`${urlGetUserById}/${id}`)
            .then((response) => response.json())
            .then((user) => {

                modalForm.id.value = user.id
                modalForm.name.value = user.name
                modalForm.surname.value = user.surname
                modalForm.age.value = user.age
                modalForm.email.value = user.email
                modalForm.pass.value = user.pass;

                const userRoleSelect = modalForm.allRoles
                const userRoles = user.roles

                fetch(urlGetAllRoles)
                    .then((response) => response.json())
                    .then((data) => {
                        setSelectOpt(data)
                    })
                const setSelectOpt = (roles) => {
                    userRoleSelect.size = roles.length
                    for (let i = 0; i < roles.length; i++) {
                        let roleName = roles[i].name
                        let roleText = roles[i].name.replace(
                            roles[i].name.substring(0, 5),
                            ""
                        )
                        let el = document.createElement("option")
                        el.textContent = roleText
                        el.value = roleName
                        userRoles.forEach((e) => {
                            if (e.name === roleName) {
                                el.selected = true
                            }
                        })
                        userRoleSelect.appendChild(el)
                    }
                }


                if (mode === "edit") {
                    newPass.onchange = () => {
                        modalForm.pass.value = newPass.value;
                    }


                    submitBtn.addEventListener("click", (e) => {
                        e.preventDefault()
                        let selectedValues = [...userRoleSelect.options]
                            .filter((x) => x.selected)
                            .map((x) => x.value)
                        fetch(urlUpdateUser, {
                            method: "PUT",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify({
                                id: modalForm.id.value,
                                name: modalForm.name.value,
                                surname: modalForm.surname.value,
                                age: modalForm.age.value,
                                email: modalForm.email.value,
                                pass: modalForm.pass.value,
                                roles: selectedValues,
                            }),
                        })
                            .then((response) => response.json())
                            .then((data) => renderTable(data))
                    })
                }
                if (mode === "delete") {
                    let selectedValues = [...userRoleSelect.options]
                        .filter((x) => x.selected)
                        .map((x) => x.value)
                    submitBtn.addEventListener("click", (e) => {
                        e.preventDefault()
                        fetch(`${urlDeleteUser}/${id}`, {
                            method: "DELETE",
                        })
                            .then((response) => response.json())
                            .then((data) => renderTable(data))
                    })
                }
            })
    }
})
//пароли
//новый пользователь