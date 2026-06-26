console.log("Dashboard JS Loaded");
const API = "http://localhost:8080/api/wallet";

let currentUser =
    JSON.parse(sessionStorage.getItem("currentUser"));

    if (!currentUser) {
        window.location.href = "index.html";
    }

    const welcomeUser = document.getElementById("welcomeUser");
    const balanceElement = document.getElementById("balance");
    const accountId = document.getElementById("accountId");
    const transactionList = document.getElementById("transactionList");

    accountId.textContent = "Account ID: " + currentUser.id;

    async function loadDashboard() {

        await loadUser();
        await loadTransactions();

    }

    async function loadUser() {
            console.log("Inside loadUser()");
        try {

            const response = await fetch(
                `${API}/user/${currentUser.id}`
            );

            if (!response.ok) {
                throw new Error("Unable to load user.");
            }

            const user = await response.json();
            console.log(user);

            // Update the in-memory user
            currentUser = user;

            // Update session storage
            sessionStorage.setItem(
                "currentUser",
                JSON.stringify(user)
            );

            welcomeUser.textContent =
                `Welcome, ${currentUser.name}`;

            balanceElement.textContent =
                `₱${currentUser.balance.toFixed(2)}`;

            accountId.textContent =
                "Account ID: " + currentUser.id;

        }

        catch (error) {

            console.error(error);

            alert("Unable to load user.");

        }

    }

    async function loadTransactions() {

        try {

            const response = await fetch(
                `${API}/transactions/${currentUser.id}`
            );

            if (!response.ok) {
                throw new Error();
            }

            const transactions =
                await response.json();

            transactionList.innerHTML = "";

            if (transactions.length === 0) {

                transactionList.innerHTML =
                    "<p class='empty'>No transactions found.</p>";

                return;

            }

            transactions.forEach(transaction => {

                const div =
                    document.createElement("div");

                div.className =
                    "transaction-item";

                div.innerHTML = `
                    <strong>${transaction.type}</strong><br>
                    Amount: ₱${transaction.amount.toFixed(2)}<br>
                    ${transaction.description}<br>
                    ${new Date(transaction.transactionDate).toLocaleString()}
                `;

                transactionList.appendChild(div);

            });

        }

        catch (error) {

            console.error(error);

        }

    }

    document
    .getElementById("logoutBtn")
    .addEventListener("click", () => {

        sessionStorage.removeItem("currentUser");

        window.location.href = "index.html";

    });

    document
    .getElementById("cashInBtn")
    .addEventListener("click", cashIn);

    async function cashIn() {

        const amount =
            parseFloat(
                document
                .getElementById("cashInAmount")
                .value
            );

        if (!amount || amount <= 0) {

            alert("Enter a valid amount.");

            return;

        }

        try {

            const response =
                await fetch(
                    `${API}/cashin`,
                    {

                        method: "POST",

                        headers: {
                            "Content-Type":
                            "application/json"
                        },

                        body: JSON.stringify({

                            userId: currentUser.id,

                            amount: amount

                        })

                    }
                );

            const result =
                await response.text();

            alert(result);

            document
            .getElementById("cashInAmount")
            .value = "";

            await loadDashboard();

        }

        catch (error) {

            console.error(error);

            alert("Unable to connect.");

        }

        document
        .getElementById("transferBtn")
        .addEventListener("click", transferMoney);

    async function transferMoney() {

        const receiverEmail =
            document.getElementById("receiverEmail").value.trim();

        const amount =
            parseFloat(
                document.getElementById("transferAmount").value
            );

        if (!receiverEmail) {
            alert("Please enter the receiver's email.");
            return;
        }

        if (
            receiverEmail.toLowerCase() ===
            currentUser.email.toLowerCase()
        ) {
            alert("You cannot transfer to your own account.");
            return;
        }

        if (!amount || amount <= 0) {
            alert("Enter a valid amount.");
            return;
        }

        try {

            const response = await fetch(
                `${API}/transfer`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        senderId: currentUser.id,
                        receiverEmail: receiverEmail,
                        amount: amount
                    })
                }
            );

            const result = await response.text();

                if (!response.ok || result === "You cannot transfer to your own account.") {
                    alert(result);
                    return;
                }

                alert(result);

                document.getElementById("receiverEmail").value = "";
                document.getElementById("transferAmount").value = "";

                await loadDashboard();

        } catch (error) {

            console.error(error);

            alert("Unable to connect to the server.");

        }
    }
    }

    document
        .getElementById("changePinBtn")
        .addEventListener("click", changePin);

    async function changePin() {

        const oldPin =
            document.getElementById("oldPin").value.trim();

        const newPin =
            document.getElementById("newPin").value.trim();

        if (!oldPin || !newPin) {
            alert("Please complete all fields.");
            return;
        }

        if (newPin.length !== 4) {
            alert("New PIN must be exactly 4 digits.");
            return;
        }

        try {

            const response = await fetch(
                `${API}/change-pin`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        userId: currentUser.id,
                        oldPin: oldPin,
                        newPin: newPin
                    })
                }
            );

            const result = await response.text();

            alert(result);

            document.getElementById("oldPin").value = "";
            document.getElementById("newPin").value = "";

        } catch (error) {

            console.error(error);

            alert("Unable to connect to the server.");

        }

    }

    async function loadDashboard() {

    console.log("Inside loadDashboard()");

    await loadUser();
    await loadTransactions();

}
    console.log("Calling loadDashboard()");
    loadDashboard();
