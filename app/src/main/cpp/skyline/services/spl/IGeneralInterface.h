// SPDX-License-Identifier: MPL-2.0
// Copyright © 2020 Skyline Team and Contributors (https://github.com/skyline-emu/)

#pragma once

#include <kernel/types/KEvent.h>
#include <services/serviceman.h>

namespace skyline::service::spl {
    /**
     * @brief IRandomInterface or "Random Number Generator API" is a randomize module
     * @url https://github.com/Ryujinx/Ryujinx/blob/00ce9eea620652b97b4d3e8cd9218c6fccff8b1c/Ryujinx.HLE/HOS/Services/Spl/IRandomInterface.cs
     */
    class IGeneralInterface : public BaseService {
      public:
        IGeneralInterface(const DeviceState &state, ServiceManager &manager);

      private:

    };
}
