package at.aoi

class CMSUser extends User<CMSContestPlatform> {
    static belongsTo = [contest: CMSContest]
    Long cmsId
}
