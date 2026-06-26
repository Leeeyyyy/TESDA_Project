const loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", async function (e) {

        e.preventDefault();

        const email =
            document.getElementById("email").value.trim();

        const pin =
            document.getElementById("pin").value.trim();

        const message =
            document.getElementById("message");

        message.innerHTML = "";

        try {

            const response = await fetch(
                "http://localhost:8080/api/auth/login",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        email,
                        pin
                    })
                }
            );

            if (!response.ok) {

                message.innerHTML =
                    '<p class="error">Invalid Email or PIN</p>';

                return;

            }

            const user = await response.json();

            sessionStorage.setItem(
                "currentUser",
                JSON.stringify(user)
            );

            window.location.href = "dashboard.html";

        }

        catch (error) {

            console.error(error);

            message.innerHTML =
                '<p class="error">Unable to connect to the server.</p>';

        }

    });

}