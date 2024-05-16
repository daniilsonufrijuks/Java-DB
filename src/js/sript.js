window.onload = function() {
    fetchRegisteredClients();
};

function fetchRegisteredClients() {
    fetch('http://localhost:8080/clients')
    .then(response => response.json())
    .then(data => {
        displayClients(data);
    })
    .catch(error => console.error('Error fetching clients:', error));
}

function displayClients(clients) {
    var clientsList = document.getElementById('clientsList');
    clientsList.innerHTML = '';

    if (clients.length === 0) {
        clientsList.innerHTML = '<p>No registered clients</p>';
        return;
    }

    var table = document.createElement('table');
    table.innerHTML = `
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
    `;

    clients.forEach(client => {
        var row = document.createElement('tr');
        row.innerHTML = `
            <td>${client.name}</td>
            <td>${client.email}</td>
            <td>${client.phone}</td>
        `;
        table.appendChild(row);
    });

    clientsList.appendChild(table);
}
