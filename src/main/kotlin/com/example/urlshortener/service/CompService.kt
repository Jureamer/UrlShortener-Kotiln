package com.example.urlshortener.service

import com.example.urlshortener.repository.CompRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.stereotype.Service


@Service
class CompService (
    val compRepository: CompRepository

) {
    @Autowired
    var context: ApplicationContext = null
    var env: Environment = context.getEnvironment()

    private fun attachServerUrl(url: String): String {
        return env.getProperty("SERVER_HOST") + '/' + url
    }

    private fun shortenUrl(longUrl: String) {
        val result = compRepository.findOne({ longUrl: longUrl })

        result?.let {
            return makeResponseForm(result)
        } ?: run {
            val shortUrl = generateShortUrl()
            val body = new Url(longUrl, shortUrl)
            compRepository.persistAndFlush(body)
            return makeResponseForm(body)
        }
    }
}


//        fun attachServerUrl(url: string): string {
//            return process.env.SERVER_HOST + '/' + url
//        }
//        func shortenUrl(longUrl: string) {
//            // longUrl 유효성 체크
//            const isValidate = await this.checkValidation(longUrl)
//
//            if (!isValidate) {
//                return null
//            }
//
//            const makeResponseForm = (response: Url) => {
//                response.shortUrl = this.attachServerUrl(response.shortUrl)
//                response.expirationAt = convertYYYYMMDD(response.expirationAt)
//                return response
//            }
//
//            // 기존에 등록된 URL이면 기존의 shortUrl을 반환
//            const result = await this.compRepository.findOne({ longUrl: longUrl })
//
//            if (result) {
//                return makeResponseForm(result)
//            } else {
//                // 등록되지 않았다면 새로운 Url 만들어서 반환
//                const shortUrl = await this.generateShortUrl()
//                const body = new Url(longUrl, shortUrl)
//                await this.compRepository.persistAndFlush(body)
//
//                return makeResponseForm(body)
//            }
//        }
//
//        async generateShortUrl(): Promise<string> {
//            let randomId7 = getRandomId(7)
//            const isExist = await this.compRepository.findOne({ shortUrl: randomId7 })
//
//            if (isExist) {
//                randomId7 = getRandomId(7)
//            }
//            return randomId7
//        }
//
//        fun getRedirectUrl(shortUrl: string): Promise<string> {
//            try {
//                const result = await this.compRepository.findOneOrFail({ shortUrl })
//                // 조회 할 경우 한달 뒤로 업데이트
//                await this.compRepository.nativeUpdate(
//                    { id: result.id },
//                    { expirationAt: getOneMonthAfterBasedCurrentDate() },
//                )
//                return result.longUrl
//            } catch (err) {
//                return undefined
//            }
//        }
//    }
//
//}