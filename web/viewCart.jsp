<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>View Cart</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

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


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>

            function buy(sid)
            {
                console.log(sid);
                var id = document.getElementById("oid").value;
                var userId = document.getElementById("userId").value;
                var stockName = document.getElementById("stname_" + sid).value;
                var quantity = document.getElementById("qty_" + sid).value;
                var totalPrice = document.getElementById("tprice_" + sid).value;
                var availability = document.getElementById("avail_" + sid).value;
                $.ajax({
                    url: 'buystock',
                    method: 'POST',
                    data: {id: id, stockId: sid, stockName: stockName, availability: availability, quantity: quantity, userId: userId, totalPrice: totalPrice},
                    success: function (resultText) {
                        $('#result').html(resultText);

                    },
                    error: function (jqXHR, exception) {
                        console.log('Error occured!!');
                    }
                });
            }
            function removecart()
            {

                var id = document.getElementById("oid").value;
                console.log(id);
                var userId = document.getElementById("userId").value;
                console.log(userId);
                $.ajax({
                    url: 'removecart',
                    method: 'POST',
                    data: {id: id, userId: userId},
                    success: function (resultText) {
                        $('#result1').html(resultText);

                    },
                    error: function (jqXHR, exception) {
                        console.log('Error occured!!');
                    }
                });
            }
        </script>
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



                    <li class="nav-item dropdown">
                        <a class="nav-link nav-icon" href="showorderlist?userId=<s:property value="#session.userId" />">
                            <i class="bi bi-gift"></i>
                            <span class="badge bg-primary badge-number"></span>
                        </a>
                    </li>



                    <li class="nav-item dropdown">

                        <a class="nav-link nav-icon" href="showwishlist?userId=<s:property value="#session.userId" />" data-bs-toggle="dropdown">
                            <i class="bi bi-cart-plus"></i>
                            <span class="badge bg-primary badge-number"><s:property value="#session.addtocart" /></span>
                        </a><!-- End AddToCart Icon -->

                    </li><!-- End AddToCart Nav -->





                    <li class="nav-item dropdown pe-3">

                        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                            <i class="bi bi-person-circle"  alt="Profile" class="rounded-circle"></i>
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
                                <a class="dropdown-item d-flex align-items-center" href="users-profile.jsp">
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
                    <a class="nav-link collapsed" href="reportstockuser">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li><!-- End Dashboard Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="user-profile.jsp">
                        <i class="bi bi-person"></i><span>User Profile</span></i>
                    </a>



                </li><!-- End Components Nav -->


                <li class="nav-item">
                    <a class="nav-link collapsed" href="reportviewstock">
                        <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                        <span>Stock List</span>
                    </a>
                </li><!-- End Profile Page Nav -->

                <li class="nav-item">
                    <a class="nav-link" href="showwishlist?userId=<s:property value="#session.userId" />">
                        <i class="bi bi-cart-check"></i>
                        <span>Wishlist</span>
                    </a>
                </li><!-- End wishlist Page Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="showorderlist?userId=<s:property value="#session.userId" />">
                        <i class="bi bi-gift"></i>
                        <span>Portfolio</span>
                    </a>
                </li> 
            </ul>

        </aside><!-- End Sidebar-->

        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Shopping Cart</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href=reportstockuser>Home</a></li>
                        <li class="breadcrumb-item active">Shopping Cart</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section dashboard">
                <div class="row">

                    <!-- Left side columns -->
                    <div class="col-lg-8">
                        <div class="row">
                            <style type="text/css">
                                .button-save {
                                    background-color: green;
                                    color: white;
                                }
                                .button-productshow {
                                    background-color: #000000;
                                    color: white;
                                    margin-left: 30%;
                                }
                            </style>
                            <a href="showwishlist.action?userId=<s:property value="#session.userId"/>"><button type="button" class="btn btn-outline-primary">Show Updated Cart</button></a>
                            <span id="result"></span>
                            <span id="result1"></span>

                            <table id="cart_tab" class="table table-bordered table-striped table-hover">
                                <s:if test="noData==false">
                                    <tr>
                                        <th>stock name</th>
                                        <th>quantity</th>
                                        <th>Availability</th>
                                        <th> Total price</th>

                                        <th>Action           </th>
                                    </tr>
                                    <s:iterator value="wishList">
                                        <tr id="cart_tr">
                                            <td style="display:none"><input type="text" id='userId' value='<s:property value="#session.userId" />' readonly></td>
                                            <td style="display:none"><input type="text" id='oid' value='<s:property value="id" />' readonly></td>
                                            <td><input type="text" id='stname_<s:property value="stockId" />' value='<s:property value="stockName" />' readonly></td>
                                            <td><input type="text" id='qty_<s:property value="stockId" />' value='<s:property value="quantity" />' readonly></td>
                                            <td><input type="text" id='avail_<s:property value="stockId" />' value='<s:property value="availability" />' readonly></td>
                                            <td><input type="text" id='tprice_<s:property value="stockId" />' value='<s:property value="totalPrice" />' readonly></td>

                                            <td style="display:inline-block"><button type="submit" onclick="buy(<s:property value="stockId" />)" class="btn btn-outline-primary">Buy</button>
                                                <button type="submit" onclick="removecart()" class="btn btn-outline-primary">Remove</button>
                                            </td>
                                            <!-- comment -->
                                        </tr>


                                    </s:iterator>

                                </table>
                            </s:if>
                            <s:else>
                                <div style="color: red;">No Data Found.</div>
                            </s:else>
                        </div>

                    </div>
                </div><!-- End Shopping Cart table -->

                <div></div>

            </section>


        </main><!-- End #main -->

        <!-- ======= Footer ======= -->


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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