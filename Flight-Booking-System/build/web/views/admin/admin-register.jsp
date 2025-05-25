<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Account</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts - Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <!-- Font Awesome for Google Icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <!-- Google Sign-In API -->
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <style>
        body {
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Poppins', sans-serif;
        }
        .register-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 3rem;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
            max-width: 450px;
            width: 100%;
            backdrop-filter: blur(10px);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 2rem;
            color: #2c3e50;
            font-weight: 600;
            font-size: 1.8rem;
        }
        .form-control {
            border-radius: 10px;
            border: 1px solid #e0e0e0;
            padding: 0.8rem;
            transition: all 0.3s ease;
            font-size: 0.9rem;
        }
        .form-control:focus {
            box-shadow: 0 0 12px rgba(116, 235, 213, 0.4);
            border-color: #74ebd5;
        }
        .form-label {
            font-size: 0.9rem;
            color: #2c3e50;
            font-weight: 500;
        }
        .btn-primary {
            background: linear-gradient(90deg, #74ebd5, #acb6e5);
            border: none;
            border-radius: 10px;
            padding: 0.9rem;
            width: 100%;
            font-weight: 500;
            font-size: 1rem;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(116, 235, 213, 0.4);
        }
        .btn-google {
            background: #ffffff;
            color: #2c3e50;
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            padding: 0.9rem;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 12px;
            font-weight: 500;
            font-size: 1rem;
            transition: all 0.3s ease;
        }
        .btn-google:hover {
            background: #f8f9fa;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        .login-link {
            text-align: center;
            margin-top: 1.5rem;
        }
        .login-link a {
            color: #74ebd5;
            text-decoration: none;
            font-size: 0.9rem;
        }
        .login-link a:hover {
            color: #acb6e5;
            text-decoration: underline;
        }
        .alert {
            border-radius: 10px;
            font-size: 0.9rem;
            margin-bottom: 1.5rem;
        }
        .form-group-row {
            display: flex;
            gap: 1rem;
        }
        .form-group-row .form-group {
            flex: 1;
        }
        .required {
            color: red;
        }
        .or-divider {
            text-align: center;
            margin: 1.5rem 0;
            font-size: 0.9rem;
            color: #2c3e50;
            position: relative;
        }
        .or-divider::before,
        .or-divider::after {
            content: '';
            position: absolute;
            top: 50%;
            width: 45%;
            height: 1px;
            background: #e0e0e0;
        }
        .or-divider::before {
            left: 0;
        }
        .or-divider::after {
            right: 0;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Register Account</h2>
        
        <!-- Manual Registration Form -->
        <form action="register" method="post">
            <div class="form-group-row">
                <div class="form-group">
                    <label for="firstName" class="form-label">First Name <span class="required">*</span></label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" required>
                </div>
                <div class="form-group">
                    <label for="lastName" class="form-label">Last Name <span class="required">*</span></label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email <span class="required">*</span></label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password <span class="required">*</span></label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
            </div>
            <div class="mb-3">
                <label for="confirm-password" class="form-label">Confirm Password <span class="required">*</span></label>
                <input type="password" class="form-control" id="confirm-password" name="confPassword" placeholder="Confirm password" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
        <div class="or-divider">Or</div>
        <!-- Google Sign-In Button -->
        <div>
            <button class="btn btn-google" onclick="triggerGoogleSignIn()">
                <i class="fab fa-google"></i> Sign up with Google
            </button>
        </div>
        <div class="login-link">
            Already have an account? <a href="login.jsp">Login</a>
        </div>
    </div>

    <!-- Bootstrap JS and Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Google Sign-In Handling -->
    <script>
        function triggerGoogleSignIn() {
            google.accounts.id.initialize({
                client_id: 'YOUR_GOOGLE_CLIENT_ID',
                callback: handleGoogleSignIn
            });
            google.accounts.id.prompt();
        }

        function handleGoogleSignIn(response) {
            const idToken = response.credential;
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'googleRegister';
            const tokenInput = document.createElement('input');
            tokenInput.type = 'hidden';
            tokenInput.name = 'id_token';
            tokenInput.value = idToken;
            form.appendChild(tokenInput);
            document.body.appendChild(form);
            form.submit();
        }
    </script>
</body>
</html>