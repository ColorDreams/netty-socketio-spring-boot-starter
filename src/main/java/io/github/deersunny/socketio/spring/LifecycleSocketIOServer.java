package io.github.deersunny.socketio.spring;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * LifecycleSocketIOServer
 * 通过 Spring 生命周期管理 SocketIOServer，提供更灵活的服务控制。
 * SocketIOServer is managed through Spring lifecycle to provide more flexible service control.
 *
 * @author 秋辞未寒
 */
@Slf4j
public class LifecycleSocketIOServer extends SocketIOServer implements SmartLifecycle {

    private final boolean autoStartup;
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * 构造一个 LifecycleSocketIOServer 实例。
     *
     * @param configuration SocketIOServer 配置对象
     * @param autoStartup   是否自动启动
     */
    public LifecycleSocketIOServer(Configuration configuration, boolean autoStartup) {
        super(configuration);
        this.autoStartup = autoStartup;
    }

    @Override
    public boolean isAutoStartup() {
        return autoStartup;
    }

    @Override
    public void start() {
        if (!running.compareAndSet(false, true)) {
            super.start();
        }
        log.info("LifecycleSocketIOServer start successfully!");
    }

    @Override
    public void stop() {
        if (!running.compareAndSet(true, false)) {
            super.stop();
        }
        log.info("LifecycleSocketIOServer has stopped.");
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

}
