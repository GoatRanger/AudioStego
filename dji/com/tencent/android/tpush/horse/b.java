package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import java.nio.channels.SocketChannel;

public interface b {
    void a(StrategyItem strategyItem);

    void a(SocketChannel socketChannel, StrategyItem strategyItem);
}
