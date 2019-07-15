package com.xiajr.masterblog.modules.template.directive;

import com.xiajr.masterblog.modules.service.ChannelService;
import com.xiajr.masterblog.modules.template.DirectiveHandler;
import com.xiajr.masterblog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelDirective extends TemplateDirective {
    @Autowired
    private ChannelService channelService;

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer id = handler.getInteger("id", 0);
        handler.put(RESULT, channelService.getById(id)).render();
    }
}