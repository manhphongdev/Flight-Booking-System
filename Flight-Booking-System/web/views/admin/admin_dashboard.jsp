<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Overview</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Google Fonts: Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
        }
        
        .content {
            margin-left: 250px;
            padding: 30px;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card-title {
            font-weight: 600;
            color: #1a2b4f;
        }
        .table {
            border-radius: 10px;
            overflow: hidden;
        }
        .table thead {
            background-color: #17a2b8;
            color: #fff;
        }
        h1, h3 {
            color: #1a2b4f;
            font-weight: 600;
        }
        .chart-container {
            max-width: 600px;
            margin: 20px auto;
        }
    </style>
</head>
<body>
    
    <jsp:include page="admin_menu.jsp"></jsp:include>
    
    <!-- Main Content -->
    <div class="content">
        <h1>Dashboard</h1>
        <div class="row">
            <!-- Static Summary Cards -->
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <i class="bi bi-person me-2" style="font-size: 1.5rem; color: #17a2b8;"></i>
                        <h5 class="card-title">Total Users</h5>
                        <p class="card-text">1200</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <i class="bi bi-airplane me-2" style="font-size: 1.5rem; color: #17a2b8;"></i>
                        <h5 class="card-title">Total Flights</h5>
                        <p class="card-text">350</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <i class="bi bi-ticket me-2" style="font-size: 1.5rem; color: #17a2b8;"></i>
                        <h5 class="card-title">Total Bookings</h5>
                        <p class="card-text">780</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <i class="bi bi-currency-dollar me-2" style="font-size: 1.5rem; color: #17a2b8;"></i>
                        <h5 class="card-title">Total Revenue</h5>
                        <p class="card-text">$12,500</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Users -->
        <div class="col-12 mt-4">
            <h3>Recent Users</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>john@example.com</td>
                        <td>John</td>
                        <td>Doe</td>
                        <td>Active</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>jane@example.com</td>
                        <td>Jane</td>
                        <td>Smith</td>
                        <td>Active</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Charts -->
        <div class="row mt-4">
            <div class="col-md-6">
                <h3>Bookings Per Month</h3>
                <div class="chart-container">
                    <canvas id="bookingsChart"></canvas>
                </div>
            </div>
            <div class="col-md-6">
                <h3>Payment Status Distribution</h3>
                <div class="chart-container">
                    <canvas id="paymentStatusChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Chart.js Scripts -->
    <script>
        // Bar Chart: Bookings Per Month
        const bookingsChart = new Chart(document.getElementById('bookingsChart'), {
            type: 'bar',
            data: {
                labels: ['Jan 2025', 'Feb 2025', 'Mar 2025', 'Apr 2025', 'May 2025'],
                datasets: [{
                    label: 'Bookings',
                    data: [150, 200, 180, 220, 190],
                    backgroundColor: '#17a2b8',
                    borderColor: '#138496',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Number of Bookings'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Month'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });

        // Pie Chart: Payment Status Distribution
        const paymentStatusChart = new Chart(document.getElementById('paymentStatusChart'), {
            type: 'pie',
            data: {
                labels: ['Completed', 'Pending', 'Failed'],
                datasets: [{
                    data: [500, 200, 80],
                    backgroundColor: ['#17a2b8', '#ffc107', '#dc3545'],
                    borderColor: ['#138496', '#e0a800', '#c82333'],
                    borderWidth: 1
                }]
            },
            options: {
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    </script>
</body>
</html>