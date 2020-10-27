package org.openstack4j.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  A simple entity which supports time data
 * 
 * @author bboyHan
 */
public interface TimeEntity {

    /**
     * created time
     * 
     * @return created time
     */
    String getCreatedTime();
	
    /**
     * updated time
     * 
     * @return updated time
     */
    String getUpdatedTime();

}
