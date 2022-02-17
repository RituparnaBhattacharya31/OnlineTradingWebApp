<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <title>Stock Update</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

             <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

         <script>

            function updateStock(sid)
            {

                var stockName = document.getElementById("stname_" + sid).value;
                var price= document.getElementById("price_" + sid).value;
                var availability=document.getElementById("avail_" + sid).value;
                $.ajax({
                    url: 'updatestock',
                    method: 'POST',
                    data: {stockId: sid,stockName:stockName, price: price, availability:availability},
                    success: function (resultText) {
                        $('#result').html(resultText);
                    },
                    error: function (jqXHR, exception) {
                        console.log('Error occured!!');
                    }
                });
            }
            function deleteStock(sid)
            {

                $.ajax({
                    url: 'deletestock',
                    method: 'POST',
                    data: {stockId: sid},
                    success: function (resultText) {
                        $('#result1').html(resultText);
                    },
                    error: function (jqXHR, exception) {
                        console.log('Error occured!!');
                    }
                });
            }
            async function updateStockList()
            {
                let formData = new FormData();           
                formData.append("csvFilePath", csvFilePath.files[0]);
                console.log(formData);
                
                await fetch('updatecsv', {
                    method: "POST", 
                    body: formData
                  }); 
                  var msg='The stock table has been uploaded successfully.';
                  $('#result2').html(msg);
                

            }

        </script>
        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">

        
    </head>

    <body>

        <!-- ======= Header ======= -->
        <header id="header" class="header fixed-top d-flex align-items-center">

            <div class="d-flex align-items-center justify-content-between">
                <a  class="logo d-flex align-items-center">
                    <img src="assets/img/logo.png" alt="">
                    <span class="d-none d-lg-block">Exa-Trade</span>
                </a>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div><!-- End Logo -->

            <div class="search-bar">
                <form class="search-form d-flex align-items-center" method="POST" action="#">
                    <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div><!-- End Search Bar -->

            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">

                    <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                            <i class="bi bi-search"></i>
                        </a>
                    </li><!-- End Search Icon-->


                    <li class="nav-item dropdown pe-3">

                        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                            
                            <span class="d-none d-md-block dropdown-toggle ps-2"><s:property value="#session.name" /></span>
                        </a><!-- End Profile Iamge Icon -->

                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                            <li class="dropdown-header">
                                <h6><s:property value="#session.name" /></h6>
                               
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="admin-profile.jsp">
                                    <i class="bi bi-person"></i>
                                    <span>My Profile</span>
                                </a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                           
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="logout">
                                    <i class="bi bi-box-arrow-right"></i>
                                    <span>Sign Out</span>
                                </a>
                            </li>

                        </ul><!-- End Profile Dropdown Items -->
                    </li><!-- End Profile Nav -->

                </ul>
            </nav><!-- End Icons Navigation -->

        </header><!-- End Header -->

        <!-- ======= Sidebar ======= -->
        <aside id="sidebar" class="sidebar">

            <ul class="sidebar-nav" id="sidebar-nav">

                <li class="nav-item">
                    <a class="nav-link collapsed" href="reportstock">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li><!-- End Dashboard Nav -->

                <li class="nav-heading">Pages</li>

                <li class="nav-item collapsed">
                    <a class="nav-link collapsed" href="admin-profile.jsp">
                        <i class="bi bi-person"></i>
                        <span>Profile</span>
                    </a>
                </li><!-- End Profile Page Nav -->
                 <li class="nav-item">
                    <a class="nav-link" href="reportupdatestock"">
                        <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                        <span>Update Stock</span>
                    </a>
                </li><!-- End Profile Page Nav -->
                
                <li class="nav-item">
                    <a class="nav-link collapsed" href="reportuser">
                        <i class="ri-user-3-line"></i>
                        <span>Promote a user to Admin</span>
                    </a>
                </li>

               
               

            </ul>

        </aside><!-- End Sidebar-->

        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Update Stocks</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="admin-dashboard.jsp">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section dashboard">
                <div class="row">

                    <!-- MAIN columns -->
                    
                        <div class="row">

                           
                            <!-- Update Stocks -->
                            <div class="col-12">
                                <div class="card recent-sales">

                                    <div class="card-body">
                  
                                        <h5 class="card-title">Stocks<span>  |  </span><span><a href="reportupdatestock"> <button type="button" class="btn btn-outline-primary">Refresh</button></a></span></h5>
                                        <span id="result"></span>
                                         <span id="result1"></span>
                                        <s:if test="noData==false">
                                             
                                                    
                                            <table class="table table-borderless datatable">
                                                <thead>
                                                    <tr>
                                                        
                                                        <th scope="col">Stock ID</th>
                                                        <th scope="col">Name</th>
                                                        <th scope="col">Price</th>
                                                        <th scope="col">Availability</th>
                                                        <th scope="col">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    
                                                    <s:iterator value="stockList">
                                                       
                                                        <tr>
                                                        
                                                            <td style="display:none"><s:property value="stockId" />"</td>
                                                            <td><s:property value="stockId" /></td>
                                                           <td style="display:none;"><s:property value="stockName" /></td>
                                                            <td><input type="text" name="stockName" id='stname_<s:property value="stockId" />' value="<s:property value="stockName" />"></td>
                                                            <td><input type="text" name="price" id='price_<s:property value="stockId" />' value="<s:property value="price" />"></td>
                                                            <td><input type="number" name="availability" id='avail_<s:property value="stockId" />' value="<s:property value="availability" />"></td>
                                                          
                                                            <td>
                                                           
                                                                    <button type="submit" onclick="updateStock(<s:property value="stockId" />)" name="submit" class="btn btn-outline-primary">Update</button>
                                                            
                                                                    <button type="submit" onclick="deleteStock(<s:property value="stockId" />)" name="submit" class="btn btn-outline-primary">Delete</button>
                                                              
<!--                                                                <input type="submit" name="submit" value="Delete" class="btn btn-outline-primary">-->
                                                                
                                                        </tr>                                                   
                                                    </s:iterator>
                                                         
                                                </tbody>
                                            </table>
                                                         
                                        </s:if>
                                        <s:else>
                                            <div style="color: red;">No Data Found.</div>
                                        </s:else>

                                    </div>
                                    
                                    
                                    
                                </div>
                            </div><!-- End Update Stocks -->
                             
                            <div style="margin:auto">
                                <span style="color:blue; font-size: 16px;"> *Download the csv from this link and upload it below</span><br><a href="https://drive.google.com/file/d/19n-atmyC7YX8Iybv_fKlrFr3zQ36_D_e/view?usp=sharing" target="_blank" rel="noopener noreferrer"> Click Here to download!</a><br>
                                <span style="color:red; font-size: 14px;" id="result2"></span><br>
                                            
                                   
                                    
                                    
                                    <input type="file" id="csvFilePath" name="csvFilePath">
                                    <button type="submit" onclick="updateStockList()" class="btn btn-outline-primary">Update</button>
                            
                            </div>
                        </div>
                    <!-- End Left side columns -->
                   

                </div>
            </section>

        </main><!-- End #main -->

        
         
        <!-- ======= Footer ======= -->
        <footer id="footer" class="footer">
            <div class="copyright">
                &copy; Copyright <strong><span>Group-A Exavalu</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
                Designed by Group-A Exavalu
            </div>
        </footer><!-- End Footer -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

         <script>
           $(document).ready(function() {
               function disableBack() {
                   window.history.forward()
               }
               window.onload = disableBack();
               window.onpageshow = function(e) {
                   if (e.persisted)
                       disableBack();
               }
           });
       </script>

    <script src=
           "https://code.jquery.com/jquery-3.6.0.min.js" 
           integrity=
   "sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
           crossorigin="anonymous"></script>
        <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.min.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.min.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>