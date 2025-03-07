// Функция для получения списка компаний
function fetchCompanies() {
    fetch('http://localhost:8080/api/v1/companies')
        .then(response => response.json())
        .then(data => {
            const companyList = document.getElementById('companyList');
            companyList.innerHTML = '';  // Очистить таблицу перед добавлением новых данных
            data.forEach(company => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${company.name}</td>
                    <td>${company.website}</td>
                    <td>${company.description}</td>
                    <td>
                        <button class="btn btn-danger" onclick="deleteCompany('${company.name}')">Delete</button>
                    </td>
                `;
                companyList.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching companies:', error));
}

// Функция для поиска компании по имени
function searchCompanyByName() {
    const name = document.getElementById('searchByName').value;
    if (name.trim()) {
        fetch(`http://localhost:8080/api/v1/companies/by-name?name=${name}`)
            .then(response => response.json())
            .then(data => {
                const companyList = document.getElementById('companyList');
                companyList.innerHTML = '';  // Очистить таблицу перед добавлением новых данных
                if (data) {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${data.name}</td>
                        <td>${data.website}</td>
                        <td>${data.description}</td>
                        <td>
                            <button class="btn btn-danger" onclick="deleteCompany('${data.name}')">Delete</button>
                        </td>
                    `;
                    companyList.appendChild(row);
                } else {
                    companyList.innerHTML = '<tr><td colspan="4" class="text-center">No company found</td></tr>';
                }
            })
            .catch(error => console.error('Error searching company:', error));
    }
}

// Функция для добавления новой компании
document.getElementById('addCompanyForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const name = document.getElementById('companyName').value;
    const website = document.getElementById('companyWebsite').value;
    const description = document.getElementById('companyDescription').value;

    fetch('http://localhost:8080/api/v1/companies/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            website: website,
            description: description
        })
    })
    .then(response => response.json())
    .then(() => {
        $('#addCompanyModal').modal('hide');  // Скрыть модальное окно после добавления
        fetchCompanies();  // Обновить список компаний
    })
    .catch(error => console.error('Error saving company:', error));
});

// Функция для удаления компании
function deleteCompany(name) {
    fetch(`http://localhost:8080/api/v1/companies/delete/by-name?name=${name}`, {
        method: 'DELETE'
    })
    .then(() => {
        fetchCompanies();  // Обновить список после удаления
    })
    .catch(error => console.error('Error deleting company:', error));
}

// Загружаем список компаний при старте страницы
document.addEventListener('DOMContentLoaded', fetchCompanies);
