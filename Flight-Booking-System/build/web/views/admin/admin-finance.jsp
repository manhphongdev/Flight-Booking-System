<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="admin_menu.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: #f3f4f6;
    }
    .main-content {
        margin-left: 300px;
        padding: 32px 24px;
    }
    @media (max-width: 900px) {
        .main-content {
            margin-left: 70px;
            padding: 16px 4px;
        }
    }
    .card {
        border-radius: 10px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.07);
        border: none;
    }
    .card-header {
        background: #2563eb;
        color: #fff;
        font-weight: 600;
        font-size: 1.2rem;
        border-radius: 10px 10px 0 0;
    }
    .table thead th {
        background: #e0e7ff;
        color: #1e293b;
        font-weight: 600;
    }
    .table tbody tr:hover {
        background: #f1f5f9;
    }
    .btn-custom {
        background: #2563eb;
        color: #fff;
        border-radius: 4px;
        border: none;
        padding: 6px 16px;
        font-weight: 500;
        transition: background 0.2s;
    }
    .btn-custom:hover {
        background: #1e40af;
    }
</style>
<div class="main-content">
    <div class="card mb-4">
        <div class="card-header">Finance Management</div>
        <div class="card-body">
            <table class="table table-hover align-middle">
                <thead>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>TXN001</td>
                        <td>2024-06-01</td>
                        <td>$1,200.00</td>
                        <td>Payment</td>
                        <td><span class="badge bg-success">Completed</span></td>
                        <td><button class="btn-custom btn-sm" data-bs-toggle="modal" data-bs-target="#financeDetailModal">View</button></td>
                    </tr>
                    <tr>
                        <td>TXN002</td>
                        <td>2024-06-02</td>
                        <td>$350.00</td>
                        <td>Refund</td>
                        <td><span class="badge bg-warning text-dark">Pending</span></td>
                        <td><button class="btn-custom btn-sm" data-bs-toggle="modal" data-bs-target="#financeDetailModal">View</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="financeDetailModal" tabindex="-1" aria-labelledby="financeDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="financeDetailModalLabel">Transaction Details</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Transaction ID:</strong> TXN001</p>
                    <p><strong>Date:</strong> 2024-06-01</p>
                    <p><strong>Amount:</strong> $1,200.00</p>
                    <p><strong>Type:</strong> Payment</p>
                    <p><strong>Status:</strong> Completed</p>
                    <p><strong>Description:</strong> Payment for booking #BK12345</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script> 