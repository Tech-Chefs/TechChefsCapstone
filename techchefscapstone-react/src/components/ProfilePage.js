function ProfilePage() {
    return (
        <>
            <div className="profile container-fluid">
                <div className="row">
                    <div className="col-2">
                        <section className="profilePicture">PP</section>
                        <img src="" class="img-fluid" alt="..."/>

                    </div>
                    <div className="col-9">
                        <section className="bio">Bio</section>
                    </div>
                </div>
                
                <div className="row">
                    <div className="col-1">
                        <aside className="settingsGutter">Settings</aside>
                    </div>
                    <div className="col-11">
                        <main className="viewport">MAIN</main>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfilePage;