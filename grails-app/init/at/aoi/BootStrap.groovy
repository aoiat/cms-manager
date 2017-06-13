package at.aoi

class BootStrap {

    def init = { servletContext ->
        CMSContestPlatform cmsContestPlatform = new CMSContestPlatform(name: "Integration",
                url: "192.168.0.19:8889").save(failOnError: true)
        new CMSContest(name: "Contest 2", cmsContestId: 2, contestPlatform: cmsContestPlatform).save(failOnError:
                true)

        print("Done")
    }
    def destroy = {
    }
}
