const registerForm = document.getElementById("registerForm");

registerForm.addEventListener("submit", async function (e) {

    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const mobileNumber = document.getElementById("number").value.trim();
    const pin = document.getElementById("pin").value.trim();

    const message = document.getElementById("message");

    message.innerHTML = "";

    if (mobileNumber.length !== 11) {
        message.innerHTML =
            '<p class="error">Mobile number must be 11 digits.</p>';
        return;
    }

    if (pin.length !== 4) {
        message.innerHTML =
            '<p class="error">PIN must be 4 digits.</p>';
        return;
    }

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name,
                    email,
                    mobileNumber,
                    pin
                })
            }
        );

        const result = await response.text();

        if (response.ok) {

            message.innerHTML =
                `<p class="success">${result}</p>`;

            registerForm.reset();

            setTimeout(() => {
                message.innerHTML +=
                    '<p class="success">You can now log in using your new account.</p>';
            }, 1500);

        } else {

            message.innerHTML =
                `<p class="error">${result}</p>`;

        }

    } catch (error) {

        console.error(error);

        message.innerHTML =
            '<p class="error">Unable to connect to the server.</p>';

    }

});