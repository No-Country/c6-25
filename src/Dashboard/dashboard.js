
let Orders = []
let amount = 0;

async function getData () {
    const request = await fetch('/api/auth/me', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          "Authorization": localStorage.getItem("token")
        },
        body: JSON.stringify(datos)
      });
    const respuesta = await request.json();

    if (respuesta.errors == null) {
        
    } else {
    Orders = respuesta.movimientos
    amount = respuesta.amount
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

async function movimientos() {
    Orders.forEach(order => {
    const tr = document.createElement('tr');
    const trContent = ` 
                        <td>${order.date}</td>
                        <td>${order.type}</td>
                        <td>${order.amount}</td>
                        <td>${order.final_balance}</td>
                        `;
    tr.innerHTML = trContent;
    document.querySelector('table tbody').appendChild(tr);
});

}
