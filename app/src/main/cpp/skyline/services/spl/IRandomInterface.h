// SPDX-License-Identifier: MPL-2.0
// Copyright © 2020 Skyline Team and Contributors (https://github.com/skyline-emu/)

#pragma once

#include <random>
#include <kernel/types/KEvent.h>
#include <services/serviceman.h>

namespace skyline::service {
    /**
     * @brief SPL ("Secure Platform services") is responsible for handling all cryptographic operations within the system and relaying them to the Secure Monitor when necessary.
     * @url https://switchbrew.org/wiki/SPL_services
     */
    namespace spl {
        /**
        * @brief IRandomInterface or 'csrng' takes an output type-0xA buffer and fills it.
        * @url https://switchbrew.org/wiki/SPL_services#GenerateRandomBytes
        */
        class IRandomInterface : public BaseService {
          private:
            std::random_device rd;
            std::mt19937 gen;
            std::uniform_int_distribution<u8> dist;

          public:
            IRandomInterface(const DeviceState &state, ServiceManager &manager);

            Result GetRandomBytes(type::KSession &session, ipc::IpcRequest &request, ipc::IpcResponse &response);

          SERVICE_DECL(
              SFUNC(0x0, IRandomInterface, GetRandomBytes)
          )
        };
    }

}