// SPDX-License-Identifier: MPL-2.0
// Copyright © 2020 Skyline Team and Contributors (https://github.com/skyline-emu/)

#include "IBcatService.h"

namespace skyline::service::bcat {
    IBcatService::IBcatService(const DeviceState &state, ServiceManager &manager) : BaseService(state, manager) {}
}
