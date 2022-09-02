let Orders = []
let amount = 0;

async function getData () {


    const request = await fetch('/api/auth/me', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          "Authorization": "Bearer "+localStorage.getItem("token")
        }
      });
    const respuesta = await request.json();

    if (respuesta.errors != undefined) {
        
    } else {
    Orders = respuesta.movimientos
    amount = respuesta.balance
    document.querySelector("#amount").innerHTML = "$" + amount;
    movimientos()    
    }
    
}

const sideMenu = document.querySelector("aside");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const themeToggler = document.querySelector(".theme-toggler");



menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
});


themeToggler.addEventListener('click', () => {
    document.body.classList.toggle('dark-theme-variables');
    themeToggler.querySelector('span:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('span:nth-child(2)').classList.toggle('active');
});

function movimientos() {
    Orders.forEach(order => {

    const date = order.date;
    const type = order.type;
    const order_amount = order.amount;
    const final_balance = order.final_balance;
    const from = order.from;
    const to = order.to;

    const tr = document.createElement('tr');
    const trContent = ` <td>${date}</td>
                        <td>${type}</td>
                        <td>${order_amount}</td>
                        <td>${final_balance}</td>
                        <td>${from}</td>
                        <td>${to}</td>
                        `;
    tr.innerHTML = trContent;
    document.querySelector('table tbody').appendChild(tr);
});

}
