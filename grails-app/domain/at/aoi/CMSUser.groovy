package at.aoi

class CMSUser extends User {
    static belongsTo = [contest: CMSContest]
    Long cmsId
}
