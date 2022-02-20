<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0);
%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home-Exatrade</title>
    <link rel="stylesheet" href="assets1/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
    <link rel="stylesheet" href="assets1/fonts/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
</head>

<body>
    <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient" style="background: rgba(140,125,129,0);color: var(--indigo);">
        <div class="container"><img src="assets1/img/logo.png" style="height: 40px;"><a class="navbar-brand text-left logo" href="#" style="color: var(--indigo);font-size: 30px;"><b>Exa-Trade</b></a><button data-toggle="collapse" class="navbar-toggler bg-primary shadow-sm" data-target="#navbarNav" style="color: var(--indigo);background: var(--indigo);border-color: var(--blue);"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarNav" style="color: var(--indigo);">
                <ul class="navbar-nav ml-auto" style="color: rgb(102, 16, 242);">
                    <li class="nav-item"><a class="nav-link active" href="index.jsp" style="color: var(--indigo);">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="pages-register.jsp" style="color: var(--indigo);">Sign-up</a></li>
                    <li class="nav-item" style="color: var(--indigo);"><a class="nav-link" href="pages-login.jsp" style="color: var(--indigo);">Login</a></li>
                
                    <li class="nav-item"><a class="nav-link" href="pages-contact.jsp" style="color: var(--indigo);">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section class="portfolio-block website gradient" style="padding-top: 120px;height: 250px;">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-12 col-lg-10 offset-lg-1 text-center text">
                    <h3>The New Face of Financial Research<br></h3>
                    <p>An intuitively designed workspace for delivering powerful market insight<br></p>
                </div>
            </div>
        </div>
    </section>
    <div><img class="img-fluid" src="assets1/img/Exa-Trade.png"></div>
    <main class="page lanidng-page">
        <section class="text-center portfolio-block block-intro" style="padding-top: 50px;padding-right: 20px;text-align: center;padding-left: 20px;padding-bottom: 30px;">
            <div class="container text-center align-content-center" style="text-align: center;padding-right: 0px;padding-left: 0px;">
                <div class="text-center justify-content-center align-items-center align-content-center about-me" style="text-align: center;">
                    <h3 style="text-align: center;">Security Snapshot View<br></h3>
                    <p class="text-center" style="font-size: 18px;">News, fundamentals, and charts accessible through a comprehensive security snapshot. Gain clarity by seeing the entire picture without missing a detail. Includes balance sheets and income statements, SEC filings, press releases, and key corporate information<br></p>
                </div>
            </div>
        </section>
    </main>
    <section class="portfolio-block skills" style="padding-top: 50px;">
        <div class="container">
            <div class="heading">
                <h2><strong>Open Free Tarding Account</strong><br></h2>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><i class="icon ion-ios-people"></i></div>
                        <div class="card-body">
                            <h3 class="card-title" style="font-size: 30px;">2.7K Users<br></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><i class="icon ion-ios-bell"></i></div>
                        <div class="card-body">
                            <h3 class="card-title" style="font-size: 30px;">1.8 Subscribers</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><i class="icon ion-ios-list-outline"></i></div>
                        <div class="card-body">
                            <h3 class="card-title" style="font-size: 30px;">4000+ products</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="portfolio-block skills" style="padding-top: 50px;background: #dadada;">
        <div class="container">
            <div class="heading">
                <h2 style="font-size: 40px;">OUR TEAM</h2>
            </div>
            <div class="row">
                
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/aditya.jpg" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                           <a href="https://www.linkedin.com/in/aditya-joarder-76658aa9"> <h3 class="card-title" style="font-size: 20px;">Aditya Joarder</h3></a>
                            <p>Digital Intern</p>
                        </div>
                    </div>
                </div>
                
                
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/ritu.png" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                          <a href="https://www.linkedin.com/in/rituparna-bhattacharya-144545190/">   <h3 class="card-title" style="font-size: 20px;">Rituparna Bhattacharya</h3> </a>
                            <p>Digital Intern</p>
                        </div>
                    </div>
                </div>
                     
                
               
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/abhineet%20(2).jpg" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                         <a href="https://www.linkedin.com/in/abhineet64">     <h3 class="card-title" style="font-size: 20px;">Abhineet Mishra</h3>   </a>
                            <p>Digital Intern<br></p>
                        </div>
                    </div>
                </div>
              
            </div>
            <div class="row">
              
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/Jaydeep.jpg" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                            <a href="https://www.linkedin.com/in/jaydeep-das-773756194">   <h3 class="card-title" style="font-size: 20px;">Jaydeep Das</h3>  </a>
                            <p>Digital Intern<br></p>
                        </div>
                    </div>
                </div>
               
                
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/suraj.jpg" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                        <a href="https://www.linkedin.com/in/suraj-kumar-7076a4194">     <h3 class="card-title" style="font-size: 20px;">Suraj Kumar</h3>  </a>
                            <p>Digital Intern<br></p>
                        </div>
                    </div>
                </div>
               
                
                <div class="col-md-4">
                    <div class="card special-skill-item border-0">
                        <div class="card-header bg-transparent border-0"><img src="assets1/img/rajshekhar.jpg" style="width: 180px;height: 200px;"></div>
                        <div class="card-body">
                          <a href="https://www.linkedin.com/in/rajasekhar-gullapalli-7273a1207">   <h3 class="card-title" style="font-size: 20px;"><strong>Gullapalli Rajshekhar</strong><br></h3>  </a>
                            <p>Digital Intern<br></p>
                        </div>
                    </div>
                </div>
               
            </div>
        </div>
    </section>
    <footer class="page-footer">
        <div class="container">
            <div class="links"><a href="#" style="color: var(--indigo);">Designed by&nbsp;<b>Exavalu Group A</b><br></a></div>
        </div>
    </footer>
    <script src="assets1/js/jquery.min.js"></script>
    <script src="assets1/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="assets1/js/theme.js"></script>
</body>

</html>