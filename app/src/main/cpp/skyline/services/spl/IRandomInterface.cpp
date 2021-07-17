// SPDX-License-Identifier: MPL-2.0
// Copyright © 2020 Skyline Team and Contributors (https://github.com/skyline-emu/)

#include "IRandomInterface.h"

namespace skyline::service::spl {
    IRandomInterface::IRandomInterface(const DeviceState &state, ServiceManager &manager) : BaseService(state, manager) {
        gen = std::mt19937(rd());
    }

    Result IRandomInterface::GetRandomBytes(type::KSession &session, ipc::IpcRequest &request, ipc::IpcResponse &response) {
        for (auto &i:request.outputBuf.at(0)) {
            i = (u8) dist(gen);
        }
        return {};
    }
}
