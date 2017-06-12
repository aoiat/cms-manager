package at.aoi.cms.v12

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

/**
 * @author Gary Ye
 */
class CMSClient {
    private final Logger LOG = LoggerFactory.getLogger(CMSClient.class)
    private final String serverUrl
    private final RestTemplate restTemplate

    CMSClient(String serverUrl) {
        this.serverUrl = serverUrl
        this.restTemplate = new RestTemplate()
    }

    boolean createUser(CMSUserDto user, CMSContestDto contest) {
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
        try {
            restTemplate.put(postURL, data, vars)
        } catch (RestClientException e) {
            LOG.error("Could not create a user ${user.username} for contest ${contest.contestId}", e)
            return false
        }
        return true
    }

    // TODO: Maybe find user
}
