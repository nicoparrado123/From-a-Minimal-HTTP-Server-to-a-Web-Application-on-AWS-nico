function greet() {
    const name = document.getElementById("name").value;
    fetch("/hello?name=" + encodeURIComponent(name))
        .then(r => r.text())
        .then(msg => document.getElementById("result").innerText = msg);
}

function getPi() {
    fetch("/pi")
        .then(r => r.text())
        .then(val => document.getElementById("pi-result").innerText = "π = " + val);
}
