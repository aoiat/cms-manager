package at.aoi.cms.v12

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
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

    static MultiValueMap<String, String> fromMap(LinkedHashMap<String, Object> map) {
        MultiValueMap<String, String> mvp = new LinkedMultiValueMap<>()
        for (a in map) {
            mvp.add(a.key, a.value.toString())
        }
        return mvp
    }

    void createUser(CMSUserDto user, CMSContestDto contest) {
        String postURL = "http://${serverUrl}/add_user/{contestId}"
        LinkedHashMap<String, Object> data = [
                first_name        : user.firstName,
                last_name         : user.lastName,
                username          : user.username,
                password          : user.password,
                email             : user.email,
                ip                : '',
                timezone          : '',
                starting_time     : '',
                delay_time        : 0,
                extra_time        : 0,
                hidden            : false,
                primary_statements: []
        ]

        def vars = [
                contestId: contest.contestId
        ]

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(fromMap(data),
                headers);
        try {
            String ignored = restTemplate.postForObject(postURL, request, String.class, vars)
            LOG.info("Received response: ${ignored}")
        } catch (RestClientException e) {
            throw e;
        }
    }

    // TODO: Maybe find user
}
