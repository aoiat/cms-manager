package at.aoi.cms.v12

import org.springframework.web.client.RestTemplate

/**
 * @author Gary Ye
 */
class CMSClient {
    private final String serverUrl
    private final RestTemplate restTemplate

    CMSClient(String serverUrl) {
        this.serverUrl = serverUrl
        this.restTemplate = new RestTemplate()
    }

    void createUser(CMSUserDto user, CMSContestDto contest) {
        String postURL = "http://{serverUrl}/contest/{contestId}/user/{username}"
        def data = [
                first_name: user.firstName,
                last_name : user.lastName,
                password  : user.password,
                email     : user.email,
        ]
        def vars = [
                serverUrl: serverUrl,
                username : user.username,
                contestId: contest.contestId
        ]
        restTemplate.put(postURL, data, vars)
    }
}
