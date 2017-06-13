package at.aoi.cms.v12

import org.junit.Test

/**
 * @author Gary Ye
 */
class CMSClientTest {
    @Test
    void createUser() throws Exception {
        CMSClient cmsClient = new CMSClient("192.168.0.19:8889");
        CMSUserDto userDto = new CMSUserDto();
        userDto.setUsername("aoi2");
        userDto.setPassword("password1");
        userDto.setFirstName("Gary");
        userDto.setLastName("Ye");
        CMSContestDto contestDto = new CMSContestDto();
        contestDto.setContestId("1");
        cmsClient.createUser(userDto, contestDto)
    }
}
