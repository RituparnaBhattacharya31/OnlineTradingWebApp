<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Portfolio</title>
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
             $(document).ready(function() {
              $("#alert").hide(); 
              $('#alertwrong').hide();
              $("#close").click(function () {
                  $('#alert').hide();
             });
             $("#closewrong").click(function () {
                  $('#alertwrong').hide();
             });
             
             });
            function sell(oid, sid)
            {
                var stockName = document.getElementById("stname_" + oid).value;
                var quantity = document.getElementById("qty_" + oid).value;
                var sellQuantity = document.getElementById("sqty_" + oid).value;
                var totalPrice = document.getElementById("tprice_" + oid).value;
                var res=parseInt(quantity)-parseInt(sellQuantity);
                var tprice = parseInt(totalPrice)-(parseInt(sellQuantity)*(parseInt(totalPrice)/parseInt(quantity)));
                if(parseInt(sellQuantity)>0  && parseInt(sellQuantity)<=parseInt(quantity))
                {
                    if(parseInt(sellQuantity)===parseInt(quantity))
                    {
                         $.ajax({
                            url: 'sellstock',
                            method: 'POST',
                            data: {orderId: oid, stockId: sid, stockName: stockName, quantityOrdered: quantity, sellQuantity: sellQuantity, totalPrice: totalPrice},
                            success: function () {
                                //$('#result').html(resultText);
                                $('#alert').show();
                                $('#buy_tr_'+oid).remove();
                                $('#alertwrong').hide();
                            },
                            error: function (jqXHR, exception) {
                                console.log('Error occured!!');
                            }
                        });
                        
                    }
                    else{
                         $.ajax({
                            url: 'sellstock',
                            method: 'POST',
                            data: {orderId: oid, stockId: sid, stockName: stockName, quantityOrdered: quantity, sellQuantity: sellQuantity, totalPrice: totalPrice},
                            success: function () {
                                //$('#result').html(resultText);
                                $('#alert').show();
                                $('#qty_'+oid).val(res.toString());
                                $('#tprice_'+oid).val(tprice.toString());
                                $('#alertwrong').hide();
                            },
                            error: function (jqXHR, exception) {
                                console.log('Error occured!!');
                            }
                        });
                    }
               }
               else
               {
//                  var msg="Please enter a valid sell quantity"; 
//                  $('#result3').html(msg);
                  $('#alertwrong').show();
                  $('#alert').hide();
               }
            
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

<!--            <div class="search-bar">
                <form class="search-form d-flex align-items-center" method="POST" action="#">
                    <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div> End Search Bar -->

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
                        <a class="nav-link nav-icon" href="showwishlist?userId=<s:property value="#session.userId" />">
                            <i class="bi bi-cart-plus"></i>
                            <span class="badge bg-primary badge-number"><s:property value="#session.addtocart" /></span>
                        </a>
                    </li>


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

                <li class="nav-item collapsed">
                    <a class="nav-link collapsed" href="user-profile.jsp">
                        <i class="bi bi-person"></i><span>User Profile</span></i>
                    </a>



                </li><!-- End Components Nav -->


                 <li class="nav-item collapsed">
                    <a class="nav-link collapsed" href="reportviewstock">
                        <i class="bi bi-file-earmark-spreadsheet-fill"></i>
                        <span>Stock List</span>
                    </a>
                </li><!-- End Profile Page Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="showwishlist?userId=<s:property value="#session.userId" />">
                        <i class="bi bi-cart-check"></i>
                        <span>Wishlist</span>
                    </a>
                </li><!-- End wishlist Page Nav -->

                <li class="nav-item">
                    <a class="nav-link" href="showorderlist?userId=<s:property value="#session.userId" />">
                        <i class="bi bi-gift"></i>
                        <span>Portfolio</span>
                    </a>
            </ul>

        </aside><!-- End Sidebar-->

        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Your Stocks</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="reportstockuser">Home</a></li>
                        <li class="breadcrumb-item active">Your Stocks</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section dashboard">
                <div class="row">

                    <!-- Left side columns -->
                    <div class="col-lg-8">
                       
                        <a href="showorderlist.action?userId=<s:property value="#session.userId"/>"><button type="button" class="btn btn-outline-primary">Show updated stocks</button></a>
                        <br>
                        <div id="alert" class="alert alert-success fade show alert-dismissible" role="alert">
                                <strong>Stock sold Successfully!</strong>
                                <button type="button" class="close" id="close" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                        </div>
<!--                        <span id="result"></span>-->
                        <div id="alertwrong" class="alert alert-danger alert-dismissible fade show" role="alert">
                            Sell quantity should be > 1 and < = quantity ordered !
                            <button type="button" class="close" id="closewrong" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                            </button>
                          </div>
<!--                        <span style="color:red;" id="result3"></span>-->
                        <table class="table table-borderless">
                            
                            <s:if test="noData==false">
                                <thead>      
                                    <tr>
                                        <th>Stock Name</th>
                                        <th>Quantity Ordered</th>
                                        <th>Date </th>
                                        <th> Total price</th>
                                        <th>Sell Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="orderList">
                                        <tr id='buy_tr_<s:property value="orderId" />'>
                                            <td style="display:none"><input type="text" id='userId' value='<s:property value="#session.userId" />' readonly></td>
                                            <td style="display:none"><input type="text" id='stid' value='<s:property value="stockId" />' readonly></td>

                                            <td><input type="text" id='stname_<s:property value="orderId" />' value='<s:property value="stockName" />' readonly></td>
                                            <td><input type="text" id='qty_<s:property value="orderId" />' value='<s:property value="quantityOrdered" />' readonly></td>
                                            <td><input type="text" id='avail_<s:property value="orderId" />' value='<s:property value="orderDate" />' readonly></td>
                                            <td><input type="text" id='tprice_<s:property value="orderId" />' value='<s:property value="totalPrice" />' readonly></td>
                                            <td><input type="number" id='sqty_<s:property value="orderId" />' value=''></td>


                                            <td style="display:inline-block"><button type="submit" onclick="sell(<s:property value="orderId" />, <s:property value="stockId" />)" class="btn btn-outline-primary">Sell</button>

                                            </td>
                                            <!-- comment -->
                                        </tr>


                                    </s:iterator>
                                </tbody>
                            </table>
                        </s:if>
                        <s:else>
                           <div style="margin-top:20px">
                                <div class="alert alert-danger" role="alert" >No Data Found.</div>
                           </div>
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