package com.ultron.boss.util

import com.ultron.boss.credit.AreaHolder
import com.ultron.boss.domain.CreditInfo
import com.ultron.boss.enums.SexEnum
import com.ultron.boss.exception.BossBizException
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils

import java.text.SimpleDateFormat

/**
 * Create By yangwei
 * Create at 2020/01/06 19:27
 * Description: 
 */

@Slf4j
@CompileStatic
class CreditUtil {

    static boolean isValidCreditNo(String creditNo) {
        if (StringUtils.isBlank(creditNo) || creditNo.length() != 18) {
            throw new BossBizException("Credit no invalid size.")
        }

        try {
            String areaNo = creditNo.substring(0, 6)
            String birth = creditNo.substring(6, 14)
            String seqNo = creditNo.substring(14, 17)

            // 验证areaNo
            if (AreaHolder.contains(areaNo)) {
                throw new BossBizException()
            }

            //验证日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd")
            sdf.setLenient(false)
            sdf.parse(birth)

            if (!seqNo.isInteger()) {
                throw new BossBizException()
            }

            String validNo = creditNo.charAt(17)
            if (validNo != "x" || Integer.parseInt(validNo) > 9) {
                throw new BossBizException()
            }
        } catch (Exception e) {
            throw new BossBizException("Invalid Credit Card No. ${e.message}")
        }

    }

    static CreditInfo getInfo(String creditNo) {
        isValidCreditNo(creditNo)
        String areaNo = creditNo.substring(0, 6)
        Integer year = creditNo.substring(6, 10) as Integer
        Integer month = creditNo.substring(10, 12) as Integer
        Integer day = creditNo.substring(12, 14) as Integer
        String seqNo = creditNo.substring(14, 17)

        Date birthDate = new Date(year, month, day)
        return new CreditInfo(
                area: AreaHolder.getByCode(areaNo),
                birthday: birthDate,
                age: getAgeByBirth(birthDate),
                sex: Integer.parseInt(seqNo) % 2 == 1 ? SexEnum.MALE : SexEnum.FEMALE,
                verified: verifyId18(creditNo)
        )
    }

    private static int getAgeByBirth(Date birthday) {
        try {
            int age
            Calendar now = Calendar.getInstance()
            now.setTime(new Date())// 当前时间

            Calendar birth = Calendar.getInstance()
            birth.setTime(birthday)

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1
                }
            }
            return age
        } catch (Exception e) {//兼容性更强,异常后返回数据
            log.info(e.message)
            return 0
        }
    }

    // 17 位加权因子
    private static final int[] RATIO_ARR = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]

    // 校验码列表
    private static final String[] CHECK_CODE_LIST = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2']

    static boolean verifyId18(String idNo) {
        // 获取身份证号字符数组
        String[] idCharArr = idNo.toCharArray() as String[]
        // 获取最后一位（身份证校验码）
        String verifyCode = idNo.charAt(17)
        // 身份证号第1-17加权和
        int idSum = 0
        // 余数
        int residue

        for (int i = 0; i < idCharArr.length - 1; i++) {
            int value = Integer.parseInt(idCharArr[i])
            idSum += value * RATIO_ARR[i]
        }
        // 取得余数
        residue = idSum % 11
        return verifyCode == CHECK_CODE_LIST[residue]
    }

    static void main(String[] args) {
        println verifyId18("210711198804204037")
    }

}
