package com.xiajr.masterblog.modules.data;

import com.xiajr.masterblog.modules.entity.PostTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : langhsu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostTagVO extends PostTag implements Serializable {
    private static final long serialVersionUID = 73354108587481371L;

    private PostVO post;
}
