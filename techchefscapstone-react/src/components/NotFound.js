function NotFound() {
    return (
        <>
            <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'></link>
            <style>
                {`
                .page_404{
                    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
                    padding:40px 0; 
                    font-family: 'Arvo', serif;
                }

                .page_404  img{
                }

                .four_zero_four_bg{
                    background-image: url(https://c.tenor.com/nNNKhOxRfIIAAAAC/james-bond-many.gif);
                    height: 275px;
                    background-position: center;
                }

                .text-center{
                    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
                }

                .contant_box_404{
                    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
                    margin - top:-50px;
                }
            `}
            </style>
            <body>
                <section class="page_404">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12 ">
                                <div class="col-sm-10 col-sm-offset-1  text-center">

                                    <h1 class="text-center">404 No Agents Found</h1>

                                    <div class="four_zero_four_bg">
                                    </div>

                                    <div class="contant_box_404">
                                        <h3 class="h2">
                                            "You can't see me!"
                                            -John Cena
                                        </h3>

                                        <p>Try somewhere else</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </body>
        </>
    )
}

export default NotFound;