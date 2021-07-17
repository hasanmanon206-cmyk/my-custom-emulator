// SPDX-License-Identifier: MPL-2.0
// Copyright © 2020 Skyline Team and Contributors (https://github.com/skyline-emu/)

#include <common/settings.h>
#include "IGeneralInterface.h"

namespace skyline::service::spl {
    IGeneralInterface::IGeneralInterface(const DeviceState &state, ServiceManager &manager) : BaseService(state, manager){}

}
